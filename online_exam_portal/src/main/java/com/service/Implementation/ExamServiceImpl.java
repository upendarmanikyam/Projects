package com.service.Implementation;

import java.util.List;
import com.examportal.entity.Exam;
import com.services.ExamService;
import com.Dao.Implementation.ExamDaoImpl;
public class ExamServiceImpl implements ExamService {
    
    private ExamDaoImpl examDao = new ExamDaoImpl();

    @Override
    public void saveExam(Exam exam) {
        examDao.save(exam);
    }

    @Override
    public Exam getExamBy_Id(long exam_Id) {
        return examDao.get(exam_Id);
    }

    @Override
    public List<Exam> getAllExams() {
        return examDao.getAll();
    }

    @Override
    public void updateExam(Exam exam) {
        examDao.update(exam);
    }

    @Override
    public void deleteExam(long exam_Id) {
        examDao.delete(exam_Id);
    }

	@Override
	public List<Exam> getExamsByCourse_Id(long course_Id) {
		return null;
	}
}
