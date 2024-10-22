package com.services;

import java.util.List;
import com.examportal.entity.Exam;
import com.examportal.entity.Instructor;

public interface InstructorService {
    void saveInstructor(Instructor instructor);
    Instructor getInstructorBy_Id(long instructor_Id);
    List<Instructor> getAllInstructors();
    void updateInstructor(Instructor instructor);
    void deleteInstructor(long instructor_Id);

    Instructor login(String name, String department, String email, String password);
    Instructor createInstructor(String name,String email, String password, String department);

   
    Exam conductExam(long instructor_Id, long course_Id, List<Long> question_Id);

	
}
