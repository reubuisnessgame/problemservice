package com.github.reubuisnessgame.gamebank.problemservice.dao;

import com.github.reubuisnessgame.gamebank.problemservice.model.TeamModel;
import com.github.reubuisnessgame.gamebank.problemservice.repository.TeamsRepository;
import com.github.reubuisnessgame.gamebank.problemservice.repository.UserRepository;
import com.github.reubuisnessgame.gamebank.problemservice.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RepositoryComponent {

    private final UserRepository userRepository;

    private final TeamsRepository teamsRepository;

    private final
    JwtTokenProvider jwtTokenProvider;


    public RepositoryComponent(UserRepository userRepository,
                               TeamsRepository teamsRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.teamsRepository = teamsRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    TeamModel getTeamByToken(String token) {
        Long userId = getUserIdFromToken(token);
        return teamsRepository.findById(userId).orElseThrow(() ->
                new UsernameNotFoundException("Team ID: " + userId + " not found"));
    }


    private Long getUserIdFromToken(String token) {
        Jws<Claims> claims = jwtTokenProvider.getClaims(resolveToken(token));
        return (Long) claims.getBody().get("userId");
    }


    TeamModel getTeamByNumber(Long number) {
        return teamsRepository.findByTeamNumber(number).orElseThrow(() ->
                new UsernameNotFoundException("Number: " + number + " not found"));
    }


    private String resolveToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("Incorrect token");
    }


}
