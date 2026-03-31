package web.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import web.celebrity.entity.Celebrity;
import web.celebrity.repository.CelebrityRepository;
import web.item.entity.Item;
import web.item.repository.ItemRepository;
import web.post.dto.PostCreateDto;
import web.post.dto.PostResponseDto;
import web.post.dto.PostUpdateDto;
import web.post.entity.Post;
import web.post.entity.PostCelebrity;
import web.post.entity.PostItem;
import web.post.repository.PostCelebrityRepository;
import web.post.repository.PostItemRepository;
import web.post.repository.PostRepository;
import web.user.entity.User;
import web.user.repository.UserRepository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CelebrityRepository celebrityRepository;
    private final PostCelebrityRepository postCelebrityRepository;
    private final PostItemRepository postItemRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public void createPost(PostCreateDto dto) {

        // 1. 로그인 유저 (임시)
        User user = userRepository.findById(1L).orElseThrow();

        // 2. 게시글 저장
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());

        postRepository.save(post);

        // 3. 연예인 처리
        for (String name : dto.getCelebrityNames()) {

            Celebrity celebrity = celebrityRepository
                    .findByName(name)
                    .orElseGet(() -> {
                        Celebrity newCeleb = new Celebrity();
                        newCeleb.setName(name);
                        return celebrityRepository.save(newCeleb);
                    });

            PostCelebrity pc = new PostCelebrity();
            pc.setPost(post);
            pc.setCelebrity(celebrity);

            postCelebrityRepository.save(pc);
        }

        // 4. 아이템 처리
        for (Long itemId : dto.getItemIds()) {

            Item item = itemRepository.findById(itemId).orElseThrow();

            PostItem pi = new PostItem();
            pi.setPost(post);
            pi.setItem(item);

            postItemRepository.save(pi);
        }

    }

    public List<PostResponseDto> getPosts(Pageable pageable) {

        Page<Post> page = postRepository.findAll(pageable);

        return page.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        return toDto(post);
    }
    private PostResponseDto toDto(Post post) {
        List<String> celebrityNames = post.getPostCelebrities()
                .stream()
                .map(pc -> pc.getCelebrity().getName())
                .collect(Collectors.toList());

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .username(post.getUser().getUsername())
                .celebrityNames(celebrityNames)
                .createdAt(post.getCreatedAt())
                .build();
    }

    @Transactional
    public void updatePost(Long postId, Long userId, PostUpdateDto dto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        // 작성자 검증
        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("수정 권한 없음");
        }

        // 값이 있을 때만 수정
        if (dto.getTitle() != null) {
            post.setTitle(dto.getTitle());
        }

        if (dto.getContent() != null) {
            post.setContent(dto.getContent());
        }
    }

    @Transactional
    public void deletePost(Long postId, Long userId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        // 작성자 검증
        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("삭제 권한 없음");
        }

        postRepository.delete(post);
    }


}