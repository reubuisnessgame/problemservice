package com.github.reubuisnessgame.gamebank.problemservice.repository;

import com.github.reubuisnessgame.gamebank.problemservice.model.ProblemModel;
import org.springframework.data.repository.CrudRepository;

public interface ProblemRepository extends CrudRepository<ProblemModel, Long> {

}
