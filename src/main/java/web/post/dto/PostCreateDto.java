package web.post.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class PostCreateDto {

    private String title;
    private String content;

    private List<String> celebrityNames;
    private List<Long> itemIds;
}