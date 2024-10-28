package com.examportal.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity // Specifies that this class is an entity and will be mapped to a table in the database.
public class Answer {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the primary key will be generated automatically.
    private long answer_Id; 

    @NotBlank(message = "Please provide an answer text") // Ensures the answer text is not empty or null.
    @Size(min = 1, max = 255, message = "Answer text must be between 1 and 255 characters") // Specifies the valid length for the answer text.
    private String answerText;
   
    @ManyToOne // Defines a many-to-one relationship with the Question entity.
    @JoinColumn(name = "question_Id", nullable = false) // Specifies the foreign key column for the relationship.
    private Question question;
    
    // Default constructor
    public Answer() {}

    // Parameterized constructor for initializing answer with provided values
    public Answer(String answerText) {
        this.answerText = answerText;
    }

    // Getter for answer_Id
    public long getAnswer_Id() {
        return answer_Id;
    }

    // Setter for answer_Id
    public void setAnswer_Id(long answer_Id) {
        this.answer_Id = answer_Id;
    }

    // Getter for answerText
    public String getAnswerText() {
        return answerText;
    }

    // Setter for answerText
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    // Getter for question
    public Question getQuestion() {
        return question;
    }

    // Setter for question
    public void setQuestion(Question question) {
        this.question = question;
    }
}
