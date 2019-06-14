package com.github.reubuisnessgame.gamebank.problemservice.model;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class AnswerModel {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private long answerId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "problem_id")
    private long problemId;

    public AnswerModel() {
    }

    public AnswerModel(long userId, long problemId) {
        this.userId = userId;
        this.problemId = problemId;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }
}
