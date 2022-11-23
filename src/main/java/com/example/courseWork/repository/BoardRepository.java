package com.example.courseWork.repository;

import com.example.courseWork.entity.Board;
import com.example.courseWork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUser(User user);
}
