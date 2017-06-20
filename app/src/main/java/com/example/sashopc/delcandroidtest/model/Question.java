package com.example.sashopc.delcandroidtest.model;

import java.util.List;


public class Question {
    public int id;
    public String questionText;
    public int type;
    public List<Answer> answers;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", type='" + type + '\'' +
                ", answers=" + answers +
                '}';
    }
}
