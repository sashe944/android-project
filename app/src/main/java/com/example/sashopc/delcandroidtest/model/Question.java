package com.example.sashopc.delcandroidtest.model;

import java.util.List;

/**
 * Created by Sasho Pc on 21.12.2016 г..
 */
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
