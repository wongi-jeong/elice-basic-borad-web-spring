package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.dto.BoardPostDTO;
import com.elice.boardproject.board.service.BoardService;
import com.elice.boardproject.board.mapper.BoardMapper;

import com.elice.boardproject.post.entity.Post;
import com.elice.boardproject.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final PostService postService;


    public BoardController(BoardService boardService, BoardMapper boardMapper, PostService postService) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.postService = postService;
    }

    // 게시판 목록 조회
    @GetMapping
    public String showBoardList(Model model) {
        model.addAttribute("boards", this.boardService.getAllBoards());

        return "board/boards";
    }

    // 게시판 조회
    @GetMapping("{boardId}")
    public String getBoard(@PathVariable Long boardId,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(required = false) String keyword,
                           Model model) {
        Board board = boardService.getBoardById(boardId);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = postService.findPostsByBoardAndKeyword(board, keyword, pageRequest);

        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);

        return "board/board";
    }

    // 게시판 생성 페이지 조회
    @GetMapping("/create")
    public String newBoard() {
        return "board/createBoard";
    }

    // 게시판 생성
    @PostMapping("/create")
    public String createBoard(@ModelAttribute BoardPostDTO boardPostDTO) {
        Board board = boardMapper.boardPostDTOBoard(boardPostDTO);
        boardService.saveBoard(board);

        return "redirect:/boards";
    }

    // 게시판 수정 페이지 조회
    @GetMapping("/{id}/edit")
    public String editBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));

        return "board/editBoard";
    }


    // 게시판 수정
    @PostMapping("/{id}/edit")
    public String editBoardPost(@PathVariable Long id, @ModelAttribute BoardPostDTO boardPostDTO) {
        Board board = boardMapper.boardPostDTOBoard(boardPostDTO).toBuilder().id(id).build();
        boardService.updateBoard(board);

        return "redirect:/boards";
    }

    // 게시판 삭제
    @DeleteMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoardById(id);

        return "redirect:/boards";
    }

}
