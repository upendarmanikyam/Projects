package com.services;

import java.util.List;
import com.examportal.entity.Answer;

public interface AnswerService {
    // Save a new answer to the database
    static void saveAnswer(Answer answer) {
        // Placeholder implementation
    } 
    
    // Retrieve an answer by its ID
    Answer getAnswerBy_Id(long answer_Id);
    
    // Retrieve a list of all answers
    List<Answer> getAllAnswers();
    
    // Update the details of an existing answer
    void updateAnswer(Answer answer);
    
    // Delete an answer by its ID
    void deleteAnswer(long answer_Id);
    
    // Retrieve a list of answers associated with a specific question ID
    List<Answer> getAnswersByQuestion_Id(long question_Id);
}
