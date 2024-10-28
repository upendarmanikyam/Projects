package com.service.Implementation;

import java.util.List;
import com.examportal.entity.Answer;
import com.examportal.entity.Question;
import com.examportal.Dao.AnswerDao;
import com.Dao.Implementation.AnswerDaoImpl;
import com.services.AnswerService;

public class AnswerServiceImpl implements AnswerService {

    // DAO instance for answer operations
    private AnswerDao answerDao = new AnswerDaoImpl();

    // Method to save an answer
    public void saveAnswer(Answer answer) {
        answerDao.save(answer); // Call the DAO to save the answer
    }

    @Override
    public Answer getAnswerBy_Id(long answer_Id) {
        return answerDao.get(answer_Id); // Retrieve answer by ID
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerDao.getAll(); // Retrieve all answers
    }

    @Override
    public void updateAnswer(Answer answer) {
        answerDao.update(answer); // Update the answer in the database
    }

    @Override
    public void deleteAnswer(long answer_Id) {
        answerDao.delete(answer_Id); // Delete the answer by ID
    }

    @Override
    public List<Answer> getAnswersByQuestion_Id(long question_Id) {
        return answerDao.getAnswersByQuestion_Id(question_Id); // Get answers related to a specific question
    }
    
    // Method to save a student's answer to a question
    public void saveStudentAnswer(Question question, String answer) {
        Answer studentAnswer = new Answer(); // Create a new Answer object
        studentAnswer.setQuestion(question); // Set the associated question
        studentAnswer.setAnswerText(answer); // Set the answer text
        saveAnswer(studentAnswer); // Save the answer using the save method
    }
}
