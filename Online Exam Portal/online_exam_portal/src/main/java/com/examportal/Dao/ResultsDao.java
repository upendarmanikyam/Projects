package com.examportal.Dao;

import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;

import java.util.List;

public interface ResultsDao {
    // Saves a new results record to the database.
    void save(Results result);
    
    // Retrieves results by their ID.
    Results get(long results_Id);
    
    // Updates an existing results record in the database.
    void update(Results result);
    
    // Deletes a results record by its ID.
    void delete(long results_Id);
    
    // Retrieves a list of all results records.
    List<Results> getAllResults();
    
    // Submits an exam for a student and records the score.
    void submitExam(Student student, Exam exam, int score);
}
