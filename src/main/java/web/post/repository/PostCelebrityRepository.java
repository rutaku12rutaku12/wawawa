package web.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.post.entity.PostCelebrity;

public interface PostCelebrityRepository extends JpaRepository<PostCelebrity, Long> {
}