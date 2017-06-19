package com.example.sashopc.delcandroidtest.model;

public class Answer {
	public int id;
	public int questionId;
	public String answerText;
	public boolean isCorrect;

	@Override
	public String toString() {
		return "Answer{" +
				"id=" + id +
				", questionId=" + questionId +
				", answerText='" + answerText + '\'' +
				", isCorrect=" + isCorrect +
				'}';
	}
}
