package web.item.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import web.brand.entity.Brand;

@Getter
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    private int price;
    private String link;
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    // 👇 생성자
    public Item(String name, String category, String imageUrl,
                int price, String link, String description, Brand brand) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
        this.link = link;
        this.description = description;
        this.brand = brand;
    }
}