package com.service.Implementation;

import java.util.List;

import com.Dao.Implementation.ResultsDaoImpl; 
import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;
import com.services.ResultsService;

public class ResultsServiceImpl implements ResultsService {
    // Instantiate the ResultsDao for database operations
    private ResultsDaoImpl resultsDao = new ResultsDaoImpl();

    @Override
    public void saveResults(Results results) {
        // Save the provided results to the database
        resultsDao.save(results);
    }

    @Override
    public List<Results> getAllResults() {
        // Retrieve and return all results from the database
        return resultsDao.getAllResults(); 
    }

    @Override
    public void updateResults(Results results) {
        // Update the provided results in the database
        resultsDao.update(results); 
    }

    @Override
    public void deleteResults(long results_Id) {
        // Delete results by their ID from the database
        resultsDao.delete(results_Id); 
    }

    @Override
    public Results getResultsBy_Id(long results_Id) {
        // Retrieve and return results by their ID
        return resultsDao.get(results_Id);
    }

    @Override
    public void submitExam(Student student, Exam exam, int score) {
        // Create a new Results object to store exam results
        Results result = new Results();
        result.setStudent(student); // Set the student who took the exam
        result.setExam(exam);       // Set the exam taken
        result.setScore(score);     // Set the score received
        
        // Save the results using the saveResults method
        saveResults(result);
    }
}
