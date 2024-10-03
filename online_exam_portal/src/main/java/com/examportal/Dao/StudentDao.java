package com.examportal.Dao;

import com.examportal.entity.Exam;
import com.examportal.entity.Student;
import java.util.List;

public interface StudentDao {
    void save(Student student);
    Student getStudent(long student_Id);
    List<Student> getAllStudents();
    Student updateStudent(long student_Id, Student updatedStudent);
    String deleteStudent(long student_Id);
    List<Student> getStudentsByCourse_Id(long course_Id);
    Student registerForExam(long student_Id, long course_Id, Exam exam);
    Student findByEmail(String email); 
    Student createStudent(Student student); 
    Student login(String name, String email, String password); 
}
