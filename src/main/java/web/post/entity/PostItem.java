package web.post.entity;

import jakarta.persistence.*;
import lombok.*;
import web.item.entity.Item;

@Entity
@Table(name = "post_items")
@Getter @Setter
@NoArgsConstructor
public class PostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}