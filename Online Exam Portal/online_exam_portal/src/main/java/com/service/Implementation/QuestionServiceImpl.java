package com.service.Implementation;

import com.Dao.Implementation.QuestionDaoImpl;
import com.examportal.Dao.QuestionDao;
import com.examportal.entity.Question;
import com.services.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    // DAO instance for interacting with the Question data
    private QuestionDao questionDao;

    // Constructor initializing the QuestionDao implementation
    public QuestionServiceImpl() {
        this.questionDao = new QuestionDaoImpl();
    }

    @Override
    public void saveQuestion(Question question) {
        // Save the provided question to the database
        questionDao.save(question);
    }

    @Override
    public Question getQuestionBy_Id(long questionId) { 
        // Retrieve and return the question by its ID
        return questionDao.get(questionId);
    }

    @Override
    public List<Question> getAllQuestions() {
        // Retrieve and return a list of all questions from the database
        return questionDao.getAll(); 
    }

    @Override
    public void updateQuestion(Question question) {
        // Update the provided question details in the database
        questionDao.update(question);
    }

    @Override
    public void deleteQuestion(long question_Id) {
        // Delete the question by its ID from the database
        questionDao.delete(question_Id); 
    }

    @Override
    public void addQuestion(Question question, long course_Id) {
        // Logic for adding a question to a specific course will be implemented here
        // You might need to associate the question with the course before saving
    }
}
