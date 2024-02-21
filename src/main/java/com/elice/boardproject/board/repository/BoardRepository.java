package com.elice.boardproject.board.repository;

import com.elice.boardproject.board.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Board save(Board board) {
        String sql = "insert into board(writer, title, content) values(?, ?, ?)";
        long boardId = jdbcTemplate.update(sql, board.getWriter(), board.getTitle(), board.getContent());
        Board.builder().boardId(boardId).build();
        return board;
    }

    public int update(Board board) {
        String sql = "update board set writer = ?, title = ?, content = ? where boardId = ?";
        return jdbcTemplate.update(sql, board.getWriter(), board.getTitle(), board.getContent(), board.getBoardId());
    }

    public void delete(Long id) {
        String sql = "delete from board where boardId = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Board> findAll() {
        String sql = "select * from board";
        return jdbcTemplate.query(sql, boardRowMapper());
    }

    public Optional<Board> findById(Long id) {
        String sql = "select * from board where boardId = ?";
        return jdbcTemplate.query(sql, boardRowMapper(), id).stream().findAny();
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> Board.builder()
                .boardId(rs.getLong("id"))
                .writer(rs.getString("writer"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .updatedDate(rs.getDate("updateDate"))
                .build();
    }
}
