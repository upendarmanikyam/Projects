package com.examportal.Dao;

import java.util.List;

import com.examportal.entity.Exam;
import com.examportal.entity.Instructor;

public interface InstructorDao {
    void save(Instructor instructor);
    
    Instructor findByEmail(String email); 
    Instructor login(String name, String department, String email, String password);
    Instructor createInstructor(Instructor instructor);
    
    Instructor get(long instructor_Id);       
    void update(Instructor instructor);         
    void delete(long instructor_Id);       
    List<Instructor> getAllInstructors();
    void conductExam(long exam_Id);
    void deleteExam(long exam_Id);
    List<Exam> getExamsByInstructor(long instructor_Id);
}
