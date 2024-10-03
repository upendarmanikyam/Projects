package com.services;

import java.util.List;
import com.examportal.entity.Question;

public interface QuestionService {
    void saveQuestion(Question question);
    Question getQuestionBy_Id(long question_Id);
    List<Question> getAllQuestions();
    void updateQuestion(Question question);
    void deleteQuestion(long question_Id);
	void addQuestion(Question question, long course_Id);
}
