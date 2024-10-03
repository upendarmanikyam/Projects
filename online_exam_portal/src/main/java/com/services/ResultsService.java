package com.services;

import java.util.List;

import com.examportal.entity.Results;

public interface ResultsService { 
	static void saveResults (Results results) {
		
	}
	Results getResultsBy_Id(long results_Id);
	 List<Results> getAllResults();
		void updateResults(Results results);
		void deleteResults(long results_Id);
		
}
