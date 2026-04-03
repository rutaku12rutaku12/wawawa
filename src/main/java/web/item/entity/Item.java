package web.item.entity;

import jakarta.persistence.*;
import lombok.*;
import web.brand.entity.Brand;

import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String imageUrl;

    private Integer price;
    private String link;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}