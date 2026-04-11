package web.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import web.post.dto.*;
import web.post.service.PostService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostCreateDto dto) {
        postService.createPost(dto);
    }

    // 전체 조회
    @GetMapping
    public List<PostResponseDto> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    // 단일 조회
    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }


    @PutMapping("/{id}")
    public void updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateDto dto,
            @AuthenticationPrincipal Long userId // JWT에서 꺼낸다고 가정
    ) {
        postService.updatePost(id, userId, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal Long userId
    ) {
        postService.deletePost(id, userId);
    }

    @PostMapping("/{postId}/items/{itemId}")
    public void addItem(
            @PathVariable Long postId,
            @PathVariable Long itemId
    ) {
        postService.addItemToPost(postId, itemId);
    }
    @GetMapping("/{postId}")
    public PostDetailResponseDto getDetail(@PathVariable Long postId) {
        return postService.getPostDetail(postId);
    }

    @GetMapping
    public Page<PostListResponseDto> getList(Pageable pageable) {
        return postService.getPostList(pageable);
    }
}