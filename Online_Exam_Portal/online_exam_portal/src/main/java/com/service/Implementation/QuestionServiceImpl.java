package com.service.Implementation;

import com.Dao.Implementation.QuestionDaoImpl;
import com.examportal.Dao.QuestionDao;
import com.examportal.entity.Question;
import com.services.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    public QuestionServiceImpl() {
        this.questionDao = new QuestionDaoImpl();
    }

    @Override
    public void saveQuestion(Question question) {
        questionDao.save(question);
    }

    @Override
    public Question getQuestionBy_Id(long questionId) { 
        return questionDao.get(questionId);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAll(); 
    }

    @Override
    public void updateQuestion(Question question) {
        questionDao.update(question);
    }

    @Override
    public void deleteQuestion(long question_Id) {
        questionDao.delete(question_Id); 
    }

	@Override
	public void addQuestion(Question question, long course_Id) {
		
	}
}
