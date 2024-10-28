package com.service.Implementation;

import java.util.List;
import com.examportal.entity.Exam;
import com.services.ExamService;
import com.Dao.Implementation.ExamDaoImpl;

public class ExamServiceImpl implements ExamService {
    
    // DAO instance for interacting with Exam data
    private ExamDaoImpl examDao = new ExamDaoImpl();

    @Override
    public void saveExam(Exam exam) {
        // Save the provided exam to the database
        examDao.save(exam);
    }

    @Override
    public Exam getExamBy_Id(long exam_Id) {
        // Retrieve and return the exam by its ID
        return examDao.get(exam_Id);
    }

    @Override
    public List<Exam> getAllExams() {
        // Retrieve and return a list of all exams from the database
        return examDao.getAll();
    }

    @Override
    public void updateExam(Exam exam) {
        // Update the provided exam details in the database
        examDao.update(exam);
    }

    @Override
    public void deleteExam(long exam_Id) {
        // Delete the exam by its ID from the database
        examDao.delete(exam_Id);
    }

    @Override
    public List<Exam> getExamsByCourse_Id(long course_Id) {
     
        return null;
    }
}
