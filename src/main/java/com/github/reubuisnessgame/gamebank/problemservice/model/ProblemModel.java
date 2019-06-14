package com.github.reubuisnessgame.gamebank.problemservice.model;

import javax.persistence.*;

@Entity
@Table(name = "problems")
public class ProblemModel {

    @Id
    @GeneratedValue
    @Column(name = "problem_id")
    private long problemId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "answer")
    private double answer;

    @Column(name = "price")
    private double price;

    public ProblemModel() {
    }

    public ProblemModel(String title, String text, double answer, double price) {
        this.title = title;
        this.text = text;
        this.answer = answer;
        this.price = price;
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
