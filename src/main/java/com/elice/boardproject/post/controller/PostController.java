package com.elice.boardproject.post.controller;

import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.dto.PostDTO;
import com.elice.boardproject.post.service.PostService;
import com.elice.boardproject.post.mapper.PostMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/{postId}")
    public String getPostById(@PathVariable Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);

        return "post/post";
    }

    @GetMapping("/create")
    public String getCreatePost(@RequestParam Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        return "post/createPost";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDTO postDTO, @RequestParam Long boardId) {
        Post post = postMapper.postDTOToPost(postDTO);
        Post createdPost = postService.createPost(post, boardId);
        return "redirect:/boards/" + createdPost.getBoard().getId();
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "post/editPost";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, @ModelAttribute PostDTO postDTO, RedirectAttributes redirectAttributes) {
        Post post = postMapper.postDTOToPost(postDTO);
        Post updatePost = postService.updatePost(post, postId);

        redirectAttributes.addAttribute("postId", updatePost.getId());
        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
        postService.deletePost(postId);
        redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
        return "redirect:/posts";
    }
}
