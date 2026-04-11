package web.post.dto;

import lombok.Getter;

@Getter
public class PostListResponseDto {

    private Long id;
    private String title;
    private String thumbnailUrl;
    private int likeCount;

    public PostListResponseDto(Long id, String title, String thumbnailUrl, int likeCount) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.likeCount = likeCount;
    }
}