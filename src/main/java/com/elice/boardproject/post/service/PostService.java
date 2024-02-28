package com.elice.boardproject.post.service;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.service.BoardService;
import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardService boardService;

    public PostService(PostRepository postRepository, BoardService boardService) {
        this.postRepository = postRepository;
        this.boardService = boardService;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post, Long boardId) {
        Board boardToSave = boardService.getBoardById(boardId);
        post.setBoard(boardToSave);

        return postRepository.save(post);
    }

    public Post updatePost(Post post, Long postId) {
        post.setId(postId);
        Post foundPost = postRepository.findById(post.getId())
                .orElse(null);

        Optional.ofNullable(post.getTitle())
                .ifPresent(title -> foundPost.setTitle(title));

        Optional.ofNullable(post.getContent())
                .ifPresent(content -> foundPost.setContent(content));

        return postRepository.save(foundPost);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        postRepository.delete(post);
    }

    public Page<Post> findPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest) {
        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findAllByBoardAndTitleContaining(board, keyword, pageRequest);
        } else {
            return postRepository.findAllByBoardOrderByBoardCreateAtDesc(board, pageRequest);
        }
    }
}
