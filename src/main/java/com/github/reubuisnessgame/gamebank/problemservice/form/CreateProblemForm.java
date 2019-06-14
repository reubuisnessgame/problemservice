package com.github.reubuisnessgame.gamebank.problemservice.form;

public class CreateProblemForm {

    private String title;

    private String text;

    private double answer;

    private double price;

    public CreateProblemForm(String title, String text, double answer, double price) {
        this.title = title;
        this.text = text;
        this.answer = answer;
        this.price = price;
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
