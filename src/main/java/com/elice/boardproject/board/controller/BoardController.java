package com.elice.boardproject.board.controller;

import com.elice.boardproject.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
public class BoardController{

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시판 목록 조회
    @GetMapping("/boards")
    public String showBoardList(Model model) {
        model.addAttribute("boards", this.boardService.getAllBoards());
        return "board/boards";
    }

    // 게시판 생성 페이지 조회
    @GetMapping("/boards/new")
    public String newBoard() {
        return "board/createBoard";
    }

    // 게시판 조회
    @GetMapping("/boards/{id}")
    public String getBorad(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        return "board/board";
    }

    // 게시판 수정 페이지 조회
    @GetMapping("boards/edit/{id}")
    public String editBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        return "board/editBoard";
    }

}
