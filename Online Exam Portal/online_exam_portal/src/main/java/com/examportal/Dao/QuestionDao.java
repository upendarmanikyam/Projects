package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Question;

public interface QuestionDao {
    // Saves a new question to the database.
    void save(Question question);
    
    // Retrieves a question by its ID.
    Question get(long question_Id);
    
    // Updates an existing question in the database.
    void update(Question question);
    
    // Deletes a question by its ID.
    void delete(long question_Id);
    
    // Retrieves a list of all questions.
    List<Question> getAll();
    
    // Retrieves questions associated with a specific exam by its ID.
    List<Question> getQuestionsForExam(long exam_Id);
}
