package com.elice.boardproject.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class BoardPostDTO {
    private String title;
    private String content;
}
