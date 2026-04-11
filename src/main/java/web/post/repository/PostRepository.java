package web.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import web.post.dto.PostListResponseDto;
import web.post.entity.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
        SELECT DISTINCT p FROM Post p
        LEFT JOIN FETCH p.postItems pi
        LEFT JOIN FETCH pi.item i
        LEFT JOIN FETCH i.brand
        LEFT JOIN FETCH p.postCelebrities pc
        LEFT JOIN FETCH pc.celebrity
        WHERE p.id = :postId
    """)
    Optional<Post> findDetailById(Long postId);

    @Query("""
        SELECT new web.post.dto.PostListResponseDto(
            p.id,
            p.title,
            p.thumbnailUrl,
            p.likeCount
        )
        FROM Post p
        ORDER BY p.createdAt DESC
    """)
    Page<PostListResponseDto> findPostList(Pageable pageable);
}