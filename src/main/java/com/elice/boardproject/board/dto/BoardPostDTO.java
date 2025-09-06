package com.elice.boardproject.board.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardPostDTO {
    private String name;
    private String description;
}
