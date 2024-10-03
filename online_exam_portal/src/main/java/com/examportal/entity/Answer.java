package com.examportal.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answer_Id; 

    @NotBlank(message = "Please provide an answer text")
    @Size(min = 1, max = 255, message = "Answer text must be between 1 and 255 characters")
    private String answerText;

    @ManyToOne
    @JoinColumn(name = "question_Id", nullable = false) 
    private Question question;
    
    public Answer() {}
    public Answer(String answerText) {
        this.answerText = answerText;
    }

    public long getAnswer_Id() {
        return answer_Id;
    }

    public void setAnswer_Id(long answer_Id) {
        this.answer_Id = answer_Id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}