package com.elice.boardproject.comment.service;

import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.repository.CommentRepository;
import com.elice.boardproject.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> findCommentByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment updateComment(Long commentId, Comment comment) {
        Comment foundComment = commentRepository.findById(commentId).orElse(null);

        Optional.ofNullable(comment.getContent()).ifPresent(content -> foundComment.setContent(content));

        return commentRepository.save(foundComment);
    }

    public void deleteComment(Long commentId) {
        Comment foundComment = commentRepository.findById(commentId).orElse(null);

        commentRepository.delete(foundComment);
    }
}
