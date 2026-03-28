package web.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}