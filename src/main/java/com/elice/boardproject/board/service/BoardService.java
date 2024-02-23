package com.elice.boardproject.board.service;

import com.elice.boardproject.board.domain.Board;
import com.elice.boardproject.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Long saveBoard(Board board) {
        Board.builder().updatedDate(new Date()).build();
        Board result = boardRepository.save(board);
        return result.getBoardId();
    }

    public void updateBoard(Board board) {
        boardRepository.update(board);
    }

    public void deleteBoardById(Long id) {
        boardRepository.delete(id);
    }
}
