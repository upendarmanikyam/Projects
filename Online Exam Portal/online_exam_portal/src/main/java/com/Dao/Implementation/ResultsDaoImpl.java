package com.Dao.Implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.examportal.Dao.ResultsDao;
import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;

import java.util.List;

public class ResultsDaoImpl implements ResultsDao {
    private SessionFactory sessionFactory;

    // Constructor initializes the SessionFactory
    public ResultsDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Save a result to the database
    @SuppressWarnings("deprecation")
    @Override
    public void save(Results result) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            session.save(result); // Persist the result object
            transaction.commit(); 
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }

    // Get a result by ID
    @Override
    public Results get(long results_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Results.class, results_Id);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }

    // Update an existing result
    @SuppressWarnings("deprecation")
    @Override
    public void update(Results result) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            session.update(result); // Update the result object
            transaction.commit(); 
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }

    // Delete a result by ID
    @SuppressWarnings("deprecation")
    @Override
    public void delete(long results_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            Results results = session.get(Results.class, results_Id);
            if (results != null) {
                session.delete(results); // Remove the result object
            }
            transaction.commit(); 
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }

    // Get a list of all results
    @Override
    public List<Results> getAllResults() {
        try (Session session = sessionFactory.openSession()) {
            Query<Results> query = session.createQuery("FROM Results", Results.class);
            return query.list(); // Return the list of results
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }

    // Submit an exam and save the result
    @Override
    public void submitExam(Student student, Exam exam, int score) {
        Results result = new Results(); // Create a new Results object
        result.setStudent(student); // Set the associated student
        result.setExam(exam); // Set the associated exam
        result.setScore(score); // Set the score
        save(result); // Call the save method to persist the result
    }
}
