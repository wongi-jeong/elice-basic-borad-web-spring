package com.elice.boardproject.board.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String writer;
    private String title;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Builder
    public Board(Long boardId, String writer, String title, String content, Date updatedDate) {
        this.boardId = boardId;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.updatedDate = updatedDate;
    }

}
