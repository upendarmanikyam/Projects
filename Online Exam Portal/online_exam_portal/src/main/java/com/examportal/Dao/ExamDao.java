package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Exam;

public interface ExamDao {
    // Saves a new exam to the database.
    void save(Exam exam);
    
    // Retrieves an exam by its ID.
    Exam get(long exam_Id);
    
    // Retrieves a list of all exams.
    List<Exam> getAll();
    
    // Updates the details of an existing exam.
    void update(Exam exam);
    
    // Deletes an exam by its ID.
    void delete(long exam_Id);
}
