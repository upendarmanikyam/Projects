package com.services;

import java.util.List;
import com.examportal.entity.Question;

public interface QuestionService {
    // Save a new question to the database
    void saveQuestion(Question question);
    
    // Retrieve a question by its ID
    Question getQuestionBy_Id(long question_Id);
    
    // Get a list of all questions
    List<Question> getAllQuestions();
    
    // Update the details of an existing question
    void updateQuestion(Question question);
    
    // Delete a question by its ID
    void deleteQuestion(long question_Id);
    
    // Add a question to a specific course
    void addQuestion(Question question, long course_Id);
}
