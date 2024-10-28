package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Answer;

public interface AnswerDao {
    // Saves a new answer to the database.
    void save(Answer answer);
    
    // Retrieves an answer by its ID.
    Answer get(long answer_Id);
    
    // Updates the details of an existing answer.
    void update(Answer answer);
    
    // Deletes an answer by its ID.
    void delete(long answer_Id);
    
    // Retrieves a list of answers associated with a specific question ID.
    List<Answer> getAnswersByQuestion_Id(long question_Id);
    
    // Retrieves a list of all answers in the database.
    List<Answer> getAll();
}
