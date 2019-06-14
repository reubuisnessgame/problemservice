package com.github.reubuisnessgame.gamebank.problemservice.repository;

import com.github.reubuisnessgame.gamebank.problemservice.model.AnswerModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnswerRepository extends CrudRepository<AnswerModel, Long> {
    Optional<AnswerModel> findByUserIdAndProblemId(long userId, long problemId);
    void deleteAllByProblemId(long problemId);
}
