package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Answer;

public interface AnswerDao {
    void save(Answer answer);
    Answer get(long answer_Id);
    void update(Answer answer);
    void delete(long answer_Id);
    List<Answer> getAnswersByQuestion_Id(long question_Id);
}
