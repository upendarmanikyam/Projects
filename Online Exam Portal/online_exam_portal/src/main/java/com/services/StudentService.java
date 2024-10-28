package com.services;

import java.util.List;
import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;

public interface StudentService {
	
    // Retrieve a list of all students
    List<Student> getAllStudents();
    
    // Get a specific student by their ID
    Student getStudent(long student_Id);
    
    // Get a list of students enrolled in a specific course by course ID
    List<Student> getStudentsByCourse_Id(long course_Id);
    
    // Update the details of a specific student
    Student updateStudent(long student_Id, Student updatedStudent);
    
    // Delete a student by their ID and return a confirmation message
    String deleteStudent(long student_Id);
    
    // View the results of a specific student
    List<Results> viewResults(Student student);
    
    // Register a student for an exam in a specific course
    void registerForExam(long student_Id, long course_Id, Exam exam);
    
    // Authenticate a student during login using their name, email, and password
    Student login(String name, String email, String password); 
    
    // Create a new student record with the provided details
    Student createStudent(String name, String email, String password);
    
    // Submit an exam for a student and record their score
    void submitExam(Student student, Exam exam, int score);
}
