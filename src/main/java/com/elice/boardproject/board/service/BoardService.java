package com.elice.boardproject.board.service;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public void updateBoard(Board board) {
        boardRepository.update(board);
    }

    public void deleteBoardById(Long id) {
        boardRepository.delete(id);
    }
}
