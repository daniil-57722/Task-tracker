package com.example.courseWork.repository;

import com.example.courseWork.entity.Board;
import com.example.courseWork.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findAllByBoard(Board board);
}
