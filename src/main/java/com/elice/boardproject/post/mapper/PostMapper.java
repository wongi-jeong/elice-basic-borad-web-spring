package com.elice.boardproject.post.mapper;

import org.mapstruct.Mapper;

import com.elice.boardproject.post.domain.Post;
import com.elice.boardproject.post.dto.PostDTO;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postDTOToPost(PostDTO postDTO);
}
