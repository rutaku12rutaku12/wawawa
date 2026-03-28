package web.celebrity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.celebrity.entity.Celebrity;

import java.util.Optional;

public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {
    Optional<Celebrity> findByName(String name);
}