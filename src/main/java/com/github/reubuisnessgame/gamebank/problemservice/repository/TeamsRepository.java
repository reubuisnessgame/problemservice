package com.github.reubuisnessgame.gamebank.problemservice.repository;

import com.github.reubuisnessgame.gamebank.problemservice.model.TeamModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamsRepository extends CrudRepository<TeamModel, Long> {
    Optional<TeamModel> findByUserId(long userId);
    Optional<TeamModel> findByTeamNumber(Long number);
}
