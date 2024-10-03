package com.services;

import java.util.List;
import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudent(long student_Id);
    List<Student> getStudentsByCourse_Id(long course_Id);
    Student updateStudent(long student_Id, Student updatedStudent);
    String deleteStudent(long student_Id);
    List<Results> viewResults(Student student);
    void registerForExam(long student_Id, long course_Id, Exam exam);
    
    Student login(String name, String email, String password); 
    Student createStudent(String name, String email, String password);
	void submitExam(Student student, Exam exam, int score);
}
