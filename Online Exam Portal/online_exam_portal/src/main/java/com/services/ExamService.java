package com.services;

import java.util.List;
import com.examportal.entity.Exam;

public interface ExamService {
    // Save a new exam to the database
    void saveExam(Exam exam);
    
    // Retrieve an exam by its ID
    Exam getExamBy_Id(long exam_Id);
    
    // Get a list of all exams
    List<Exam> getAllExams();
    
    // Update the details of an existing exam
    void updateExam(Exam exam);
    
    // Delete an exam by its ID
    void deleteExam(long exam_Id);
    
    // Get a list of exams associated with a specific course
    List<Exam> getExamsByCourse_Id(long course_Id);
}
