package com.examportal.Dao;

import java.util.List;

import com.examportal.entity.Question;

public interface QuestionDao {
    void save(Question question);
    Question get(long questionId);
    void update(Question question);
    void delete(long questionId);
    List<Question> getAll();
}
