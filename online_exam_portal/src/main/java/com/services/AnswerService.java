package com.services;

import java.util.List;
import com.examportal.entity.Answer;

public interface AnswerService {
    void saveAnswer(Answer answer); // Remove static
    Answer getAnswerBy_Id(long answer_Id);
    List<Answer> getAllAnswers();
    void updateAnswer(Answer answer);
    void deleteAnswer(long answer_Id);
    List<Answer> getAnswersByQuestion_Id(long question_Id);
}
