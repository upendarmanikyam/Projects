package com.examportal.Dao;

import java.util.List;

import com.examportal.entity.Question;

public interface QuestionDao {
    void save(Question question);
    Question get(long question_Id);
    void update(Question question);
    void delete(long question_Id);
	List<Question> getAll();
	List<Question> getQuestionsForExam(long exam_Id);
}
