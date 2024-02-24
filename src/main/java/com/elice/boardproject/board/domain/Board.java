package com.elice.boardproject.board.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Column(length = 200)
    private String description;

    public Board(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
