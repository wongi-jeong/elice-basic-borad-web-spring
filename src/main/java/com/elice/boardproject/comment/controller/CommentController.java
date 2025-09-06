package com.elice.boardproject.comment.controller;

import com.elice.boardproject.comment.dto.CommentDTO;
import com.elice.boardproject.comment.entity.Comment;
import com.elice.boardproject.comment.mapper.CommentMapper;
import com.elice.boardproject.comment.service.CommentService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping
    public String createComment(@ModelAttribute CommentDTO commentDTO, @RequestParam Long postId, RedirectAttributes redirectAttributes) {
        Comment comment = commentMapper.commentDtoToComment(commentDTO);
        commentService.createComment(postId, comment);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @PostMapping("/{commentId}/edit")
    public String editComment(@PathVariable Long commentId, @ModelAttribute CommentDTO commentDTO, RedirectAttributes redirectAttributes) {
        Comment comment = commentMapper.commentDtoToComment(commentDTO);
        Comment updateComment = commentService.updateComment(commentId, comment);

        redirectAttributes.addAttribute("postId", updateComment.getPost().getId());
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/posts";
    }
}
