package com.github.reubuisnessgame.gamebank.problemservice.repository;

import com.github.reubuisnessgame.gamebank.problemservice.model.TeamModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamsRepository extends CrudRepository<TeamModel, Long> {

    Optional<TeamModel> findByTeamNumber(Long number);
}
