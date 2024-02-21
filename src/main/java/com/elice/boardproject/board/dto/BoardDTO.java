package com.elice.boardproject.board.dto;

import com.elice.boardproject.board.domain.Board;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
public class BoardDTO {

    private Long boardId;
    private String writer;
    private String title;
    private String content;
    private Date updatedDate;

    //DTO -> Entity
    public Board toEntity() {
        return Board.builder()
                .boardId(boardId)
                .writer(writer)
                .title(title)
                .content(content)
                .updatedDate(updatedDate)
                .build();
    }
}
