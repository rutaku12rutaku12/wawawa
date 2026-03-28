package web.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.celebrity.entity.Celebrity;
import web.celebrity.repository.CelebrityRepository;
import web.item.entity.Item;
import web.item.repository.ItemRepository;
import web.post.dto.PostCreateDto;
import web.post.entity.Post;
import web.post.entity.PostCelebrity;
import web.post.entity.PostItem;
import web.post.repository.PostCelebrityRepository;
import web.post.repository.PostItemRepository;
import web.post.repository.PostRepository;
import web.user.entity.User;
import web.user.repository.UserRepository;

import java.time.LocalDateTime;

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
}