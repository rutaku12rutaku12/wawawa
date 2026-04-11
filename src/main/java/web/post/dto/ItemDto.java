package web.post.dto;

import lombok.Getter;

@Getter
public class ItemDto {

    private String name;
    private String brand;

    public ItemDto(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }
}