package com.github.reubuisnessgame.gamebank.problemservice.dao;

import com.github.reubuisnessgame.gamebank.problemservice.form.ProblemFormWithoutAnswer;
import com.github.reubuisnessgame.gamebank.problemservice.model.AnswerModel;
import com.github.reubuisnessgame.gamebank.problemservice.model.ProblemModel;
import com.github.reubuisnessgame.gamebank.problemservice.model.TeamModel;
import com.github.reubuisnessgame.gamebank.problemservice.repository.AnswerRepository;
import com.github.reubuisnessgame.gamebank.problemservice.repository.ProblemRepository;
import com.github.reubuisnessgame.gamebank.problemservice.repository.TeamsRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProblemDAO {

    private final ProblemRepository problemRepository;

    private final AnswerRepository answerRepository;

    private final RepositoryComponent repositoryComponent;

    private final TeamsRepository teamsRepository;


    public ProblemDAO(ProblemRepository problemRepository, AnswerRepository answerRepository, RepositoryComponent repositoryComponent, TeamsRepository teamsRepository) {
        this.problemRepository = problemRepository;
        this.answerRepository = answerRepository;
        this.repositoryComponent = repositoryComponent;
        this.teamsRepository = teamsRepository;
    }

    public ProblemModel createNewProblem(String title, String text, double answer, double price) {
        return problemRepository.save(new ProblemModel(title, text, answer, price));
    }

    public ArrayList<ProblemFormWithoutAnswer> getAllProblems() throws NotFoundException {
        Iterable<ProblemModel> problemModels = problemRepository.findAll();
        if (!problemModels.iterator().hasNext()) {
            throw new NotFoundException("Tasks not found");
        }
        ArrayList<ProblemFormWithoutAnswer> problemFormWithoutAnswers = new ArrayList<>();
        problemModels.forEach((p) -> problemFormWithoutAnswers.add(new ProblemFormWithoutAnswer(p.getTitle(), p.getText(), p.getPrice())));
        return problemFormWithoutAnswers;
    }

    public boolean checkAnswer(String token, long problemId, double answer) {
        TeamModel teamModel = repositoryComponent.getTeamByToken(token);
        ProblemModel problemModel = problemRepository.findById(problemId).orElseThrow(()
                -> new UsernameNotFoundException("Problem with ID" + problemId + " not found."));
        if(answerRepository.findByUserIdAndProblemId(teamModel.getId(), problemModel.getProblemId()).isPresent()){
            throw new IllegalArgumentException("You have already solved this problem");
        }
        if (answer == problemModel.getAnswer()) {
            teamModel.setScore(teamModel.getScore() + problemModel.getPrice());
            answerRepository.save(new AnswerModel(teamModel.getId(), problemModel.getProblemId()));
            teamsRepository.save(teamModel);
            return true;
        } else {
            teamModel.setScore(teamModel.getScore() - problemModel.getPrice());
            teamsRepository.save(teamModel);
            return false;
        }
    }

    public void deleteById(long id){
        problemRepository.deleteById(id);
        answerRepository.deleteAllByProblemId(id);
    }

    public void clearAll(){
        answerRepository.deleteAll();
        problemRepository.deleteAll();
    }
}
