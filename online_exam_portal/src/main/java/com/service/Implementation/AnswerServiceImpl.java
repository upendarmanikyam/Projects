package com.service.Implementation;

import java.util.List;
import com.examportal.entity.Answer;
import com.examportal.Dao.AnswerDao;
import com.Dao.Implementation.AnswerDaoImpl;
import com.services.AnswerService;

public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao = new AnswerDaoImpl();

    @Override
    public void saveAnswer(Answer answer) {
        answerDao.save(answer);
    }

    @Override
    public Answer getAnswerBy_Id(long answer_Id) {
        return answerDao.get(answer_Id); 
    }

    @Override
    public List<Answer> getAllAnswers() {
        return null; 
    }

    @Override
    public void updateAnswer(Answer answer) {
        answerDao.update(answer); 
    }

    @Override
    public void deleteAnswer(long answer_Id) {
        answerDao.delete(answer_Id); 
    }

    @Override
    public List<Answer> getAnswersByQuestion_Id(long question_Id) {
        return answerDao.getAnswersByQuestion_Id(question_Id);
    }
}
