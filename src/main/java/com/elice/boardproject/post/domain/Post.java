package com.elice.boardproject.post.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
