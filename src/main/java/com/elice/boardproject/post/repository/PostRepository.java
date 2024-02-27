package com.elice.boardproject.post.repository;

import com.elice.boardproject.board.entity.Board;
import com.elice.boardproject.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 생성 일자의 내림차순으로 조회된 모든 게시판 객체를 Page로 넘겨주기
    Page<Post> findAllByBoardOrderByBoardCreateAtDesc(Board board, Pageable pageable);

    // 검색 키워드에 맞는 게시판으로 조회된 모든 게시판 객체를 Page로 넘겨주기
    Page<Post> findAllByBoardAndTitleContaining(Board board, String keyword, Pageable pageable);
}
