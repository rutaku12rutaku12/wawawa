package web.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.item.entity.Item;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post_items")
public class PostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public PostItem(Post post, Item item) {
        this.post = post;
        this.item = item;
    }
}