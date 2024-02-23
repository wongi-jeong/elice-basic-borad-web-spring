package com.elice.boardproject.post.dto;

import com.elice.boardproject.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String content;

    public Post toEntity() {
        return new Post(title, content);
    }

}
