package com.examportal.Dao;

import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;

import java.util.List;

public interface ResultsDao {
    void save(Results result);            
    Results get(long results_Id);         
    void update(Results result);          
    void delete(long results_Id);         
    List<Results> getAllResults();
    void submitExam(Student student, Exam exam, int score); 
	}

