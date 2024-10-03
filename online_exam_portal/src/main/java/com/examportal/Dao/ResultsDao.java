package com.examportal.Dao;

import com.examportal.entity.Results;
import java.util.List;

public interface ResultsDao {
    void save(Results result);            
    Results get(long results_Id);         
    void update(Results result);          
    void delete(long results_Id);         
    List<Results> getAllResults();
	}

