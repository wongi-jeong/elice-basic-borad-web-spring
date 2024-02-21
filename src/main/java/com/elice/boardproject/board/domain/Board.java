package com.elice.boardproject.board.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

}
