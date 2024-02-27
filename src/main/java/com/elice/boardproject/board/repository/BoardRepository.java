package com.elice.boardproject.board.repository;

import com.elice.boardproject.board.entity.Board;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Board> findAll() {
        String sql = "SELECT * FROM board";
        return jdbcTemplate.query(sql, boardRowMapper());
    }

    public Optional<Board> findById(Long id) {
        try {
            String sql = "SELECT * FROM board WHERE id = ?";
            Board board = jdbcTemplate.queryForObject(sql, boardRowMapper(), id);

            return Optional.ofNullable(board);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public void save(Board board) {
        String sql = "INSERT INTO board(name, description, create_at) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime createAt = LocalDateTime.now();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, board.getName());
            ps.setString(2, board.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(createAt));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();

        if (key == null) return;

        board.toBuilder()
                .id(key.longValue())
                .createAt(createAt)
                .build();
    }

    public void update(Board board) {
        String sql = "UPDATE board SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, board.getName(), board.getDescription(), board.getId());

    }

    public void delete(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> Board.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .createAt(rs.getTimestamp("create_at").toLocalDateTime())
                .build();
    }
}
