package com.elice.boardproject.board.mapper;

import com.elice.boardproject.board.domain.Board;
import com.elice.boardproject.board.dto.BoardPostDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board boardPostDTOBoard(BoardPostDTO boardPostDTO);
}
