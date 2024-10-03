package com.services;

import java.util.List;
import com.examportal.entity.Exam;

public interface ExamService {
    void saveExam(Exam exam);
    Exam getExamBy_Id(long exam_Id);
    List<Exam> getAllExams();
    void updateExam(Exam exam);
    void deleteExam(long exam_Id);
	List<Exam> getExamsByCourse_Id(long course_Id);
}
