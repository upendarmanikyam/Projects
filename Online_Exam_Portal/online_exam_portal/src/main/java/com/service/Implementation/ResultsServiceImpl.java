package com.service.Implementation;

import java.util.List;

import com.Dao.Implementation.ResultsDaoImpl; // Make sure to have a DAO for Results
import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;
import com.services.ResultsService;

public class ResultsServiceImpl implements ResultsService {
    private ResultsDaoImpl resultsDao = new  ResultsDaoImpl();

        @Override
        public void saveResults(Results results) {
            resultsDao.save(results);
        }

        @Override
        public List<Results> getAllResults() {
            return resultsDao.getAllResults(); 
        }

        @Override
        public void updateResults(Results results) {
            resultsDao.update(results); 
        }

        @Override
        public void deleteResults(long results_Id) {
            resultsDao.delete(results_Id); 
        }

        @Override
        public Results getResultsBy_Id(long results_Id) {
            return resultsDao.get(results_Id);
        }
        @Override
        public void submitExam(Student student, Exam exam, int score) {
            Results result = new Results();
            result.setStudent(student);
            result.setExam(exam);
            result.setScore(score);
            saveResults(result); // Call the save method
        }

    }


