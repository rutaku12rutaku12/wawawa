package web.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostDetailResponseDto {

    private Long id;
    private String title;

    private List<ItemDto> items;
    private List<String> celebrities;

    public PostDetailResponseDto(Long id, String title,
                                 List<ItemDto> items,
                                 List<String> celebrities) {
        this.id = id;
        this.title = title;
        this.items = items;
        this.celebrities = celebrities;
    }
}