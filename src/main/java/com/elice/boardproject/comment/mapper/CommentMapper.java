package com.elice.boardproject.comment.mapper;

import com.elice.boardproject.comment.dto.CommentDTO;
import com.elice.boardproject.comment.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentDtoToComment(CommentDTO commentDTO);
}
