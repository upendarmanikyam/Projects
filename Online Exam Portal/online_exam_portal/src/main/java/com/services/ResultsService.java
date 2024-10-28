package com.services;

import java.util.List;

import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;

public interface ResultsService { 
    // Save the results of an exam
    void saveResults(Results results);
    
    // Retrieve results by their ID
    Results getResultsBy_Id(long results_Id);
    
    // Get a list of all results
    List<Results> getAllResults();
    
    // Update the details of existing results
    void updateResults(Results results);
    
    // Delete results by their ID
    void deleteResults(long results_Id);
    
    // Submit an exam for a student and record their score
    void submitExam(Student student, Exam exam, int score); 
}
