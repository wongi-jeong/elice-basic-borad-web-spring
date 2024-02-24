package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.domain.Board;
import com.elice.boardproject.board.dto.BoardPostDTO;
import com.elice.boardproject.board.service.BoardService;
import com.elice.boardproject.board.mapper.BoardMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("boards")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;


    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

    // 게시판 목록 조회
    @GetMapping
    public String showBoardList(Model model) {
        model.addAttribute("boards", this.boardService.getAllBoards());

        return "board/boards";
    }

    // 게시판 조회
    @GetMapping("{id}")
    public String getBorad(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));

        return "board/board";
    }

    // 게시판 생성 페이지 조회
    @GetMapping("/create")
    public String newBoard() {
        return "board/createBoard";
    }

    // 게시판 생성
    @PostMapping("/boards/create")
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
    @PutMapping("/{id}/edit")
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
