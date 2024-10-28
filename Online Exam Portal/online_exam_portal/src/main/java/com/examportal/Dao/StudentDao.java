package com.examportal.Dao;

import com.examportal.entity.Exam;
import com.examportal.entity.Student;
import java.util.List;

public interface StudentDao {
    // Saves a new student record to the database.
    void save(Student student);
    
    // Retrieves a student by their ID.
    Student getStudent(long student_Id);
    
    // Retrieves a list of all students.
    List<Student> getAllStudents();
    
    // Updates an existing student's information and returns the updated student.
    Student updateStudent(long student_Id, Student updatedStudent);
    
    // Deletes a student record by their ID and returns a status message.
    String deleteStudent(long student_Id);
    
    // Retrieves a list of students enrolled in a specific course by course ID.
    List<Student> getStudentsByCourse_Id(long course_Id);
    
    // Registers a student for an exam and returns the updated student record.
    Student registerForExam(long student_Id, long course_Id, Exam exam);
    
    // Finds a student by their email address.
    Student findByEmail(String email);
    
    // Creates a new student and returns the created student record.
    Student createStudent(Student student);
    
    // Authenticates a student by their name, email, and password.
    Student login(String name, String email, String password);
}
