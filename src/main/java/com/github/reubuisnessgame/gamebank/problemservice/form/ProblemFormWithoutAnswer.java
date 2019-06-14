package com.github.reubuisnessgame.gamebank.problemservice.form;

public class ProblemFormWithoutAnswer {
    private String title;
    private String text;
    private double price;

    public ProblemFormWithoutAnswer(String title, String text, double price) {
        this.title = title;
        this.text = text;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
