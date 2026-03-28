package web.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.post.entity.PostItem;

public interface PostItemRepository extends JpaRepository<PostItem, Long> {
}