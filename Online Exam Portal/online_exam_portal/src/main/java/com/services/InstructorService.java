package com.services;

import java.util.List;
import com.examportal.entity.Exam;
import com.examportal.entity.Instructor;

public interface InstructorService {
    // Save a new instructor to the database
    void saveInstructor(Instructor instructor);
    
    // Retrieve an instructor by their ID
    Instructor getInstructorBy_Id(long instructor_Id);
    
    // Get a list of all instructors
    List<Instructor> getAllInstructors();
    
    // Update the details of an existing instructor
    void updateInstructor(Instructor instructor);
    
    // Delete an instructor by their ID
    void deleteInstructor(long instructor_Id);
    
    // Log in an instructor with their credentials
    Instructor login(String name, String department, String email, String password);
    
    // Create a new instructor with the specified details
    Instructor createInstructor(String name, String email, String password, String department);

    // Conduct an exam using the specified instructor, course, and list of question IDs
    Exam conductExam(long instructor_Id, long course_Id, List<Long> question_Id);
}
