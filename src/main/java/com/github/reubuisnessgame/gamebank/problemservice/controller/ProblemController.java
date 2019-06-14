package com.github.reubuisnessgame.gamebank.problemservice.controller;

import com.github.reubuisnessgame.gamebank.problemservice.dao.ProblemDAO;
import com.github.reubuisnessgame.gamebank.problemservice.form.CreateProblemForm;
import com.github.reubuisnessgame.gamebank.problemservice.form.StartGameForm;
import com.github.reubuisnessgame.gamebank.problemservice.model.ExceptionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/problems")
public class ProblemController {

    private final ProblemDAO problemDAO;

    public ProblemController(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    @PreAuthorize("hasAuthority('MODERATOR') or hasAuthority('TEAM')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity getAllProblems(@RequestHeader(value = "Authorization") String token) {
        try {
            HashMap<String, Object> model = new HashMap<>();
            model.put("problems", problemDAO.getAllProblems(token));
            return ResponseEntity.ok(model);
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(403).body(new ExceptionModel(403, "Forbidden", e.getMessage(), "/problems/"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ExceptionModel(400, "Bad Request", e.getMessage(), "/problems/"));
        } catch (Throwable t) {
            return ResponseEntity.status(500).body(new ExceptionModel(500, "Internal Error", t.getMessage(), "/problems/"));
        }
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createProblem(@RequestBody CreateProblemForm form) {
        try {
            return ResponseEntity.ok(problemDAO.createNewProblem(form.getTitle(), form.getText(), form.getAnswer(), form.getPrice()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ExceptionModel(400, "Bad Request", e.getMessage(), "/problems/"));
        } catch (Throwable t) {
            return ResponseEntity.status(500).body(new ExceptionModel(500, "Internal Error", t.getMessage(), "/problems/"));
        }
    }

    @PreAuthorize("hasAuthority('TEAM')")
    @RequestMapping(value = "/check/{problem_id}", method = RequestMethod.POST)
    public ResponseEntity checkAnswer(@RequestHeader(value = "Authorization") String token, @PathVariable(value = "problem_id") long problemId,
                                      @RequestParam(value = "asw") double answer) {
        try {
            HashMap<String, Object> model = new HashMap<>();
            model.put("isCorrect", problemDAO.checkAnswer(token, problemId, answer));
            return ResponseEntity.ok(model);
        }catch (IllegalAccessException e) {
            return ResponseEntity.status(403).body(new ExceptionModel(403, "Forbidden", e.getMessage(), "/problems/"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ExceptionModel(400, "Bad Request", e.getMessage(), "/problems/"));
        } catch (Throwable t) {
            return ResponseEntity.status(500).body(new ExceptionModel(500, "Internal Error", t.getMessage(), "/problems/"));
        }
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            problemDAO.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ExceptionModel(400, "Bad Request", e.getMessage(), "/problems/"));
        } catch (Throwable t) {
            return ResponseEntity.status(500).body(new ExceptionModel(500, "Internal Error", t.getMessage(), "/problems/"));
        }
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public ResponseEntity stopStartGame(@RequestBody StartGameForm form) {
        try {
            problemDAO.stopStartGame(form.isStated());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ExceptionModel(400, "Bad Request", e.getMessage(), "/problems/"));
        } catch (Throwable t) {
            return ResponseEntity.status(500).body(new ExceptionModel(500, "Internal Error", t.getMessage(), "/problems/"));
        }
    }



    @PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public ResponseEntity clearAll() {
        try {
            problemDAO.clearAll();
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ExceptionModel(400, "Bad Request", e.getMessage(), "/problems/"));
        } catch (Throwable t) {
            return ResponseEntity.status(500).body(new ExceptionModel(500, "Internal Error", t.getMessage(), "/problems/"));
        }
    }


}
