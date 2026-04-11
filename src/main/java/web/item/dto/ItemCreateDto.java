package web.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemCreateDto {

    private String name;
    private String category;
    private String imageUrl;

    private int price;
    private String link;
    private String description;

    private Long brandId;
}