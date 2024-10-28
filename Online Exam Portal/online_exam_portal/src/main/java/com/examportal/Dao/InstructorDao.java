package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Exam;
import com.examportal.entity.Instructor;

public interface InstructorDao {
    // Saves a new instructor to the database.
    void save(Instructor instructor);
    
    // Finds an instructor by their email address.
    Instructor findByEmail(String email); 
    
    // Handles instructor login with name, department, email, and password.
    Instructor login(String name, String department, String email, String password);
    
    // Creates a new instructor in the database.
    Instructor createInstructor(Instructor instructor);
    
    // Retrieves an instructor by their ID.
    Instructor get(long instructor_Id);   
    
    // Updates an existing instructor's information.
    void update(Instructor instructor);   
    
    // Deletes an instructor by their ID.
    void delete(long instructor_Id);   
    
    // Retrieves a list of all instructors.
    List<Instructor> getAllInstructors();
    
    // Allows an instructor to conduct an exam by its ID.
    void conductExam(long exam_Id);
    
    // Deletes an exam conducted by an instructor.
    void deleteExam(long exam_Id);
    
    // Retrieves exams associated with a specific instructor by their ID.
    List<Exam> getExamsByInstructor(long instructor_Id);
}
