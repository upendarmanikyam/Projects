package com.services;

import java.util.List;

import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;
public interface ResultsService { 
    void saveResults(Results results);
    Results getResultsBy_Id(long results_Id);
    List<Results> getAllResults();
    void updateResults(Results results);
    void deleteResults(long results_Id);
    void submitExam(Student student, Exam exam, int score); 
}


