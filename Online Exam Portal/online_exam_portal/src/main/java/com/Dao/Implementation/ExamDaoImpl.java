package com.Dao.Implementation;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.examportal.Dao.ExamDao;
import com.examportal.entity.Exam;

public class ExamDaoImpl implements ExamDao {
     
    private SessionFactory sessionFactory;

    // Constructor initializes the SessionFactory
    public ExamDaoImpl() {
        // Load the Hibernate configuration and build the SessionFactory
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Save a new exam to the database
    @SuppressWarnings("deprecation")
    @Override
    public void save(Exam exam) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // Begin a new transaction
            session.save(exam); // Save the exam object to the database
            transaction.commit(); // Commit the transaction to persist changes
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    // Get an exam by its ID
    @Override
    public Exam get(long exam_Id) {
        Exam exam = null;
        try (Session session = sessionFactory.openSession()) {
            // Retrieve the exam object by its ID
            exam = session.get(Exam.class, exam_Id);
            // Initialize the questions associated with the exam if it exists
            if (exam != null) {
                Hibernate.initialize(exam.getQuestions());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }
        return exam; // Return the retrieved exam object
    }

    // Get a list of all exams
    @Override
    public List<Exam> getAll() {
        try (Session session = sessionFactory.openSession()) {
            // Create a query to retrieve all exam objects
            return session.createQuery("from Exam", Exam.class).list(); // Return the list of exams
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }
        return null; // Return null if an error occurs
    }

    // Update an existing exam
    @SuppressWarnings("deprecation")
    @Override
    public void update(Exam exam) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // Begin a new transaction
            session.update(exam); // Update the exam object in the database
            transaction.commit(); // Commit the transaction to persist changes
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    // Delete an exam by its ID
    @SuppressWarnings("deprecation")
    @Override
    public void delete(long exam_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // Begin a new transaction
            // Retrieve the exam object by its ID
            Exam exam = session.get(Exam.class, exam_Id); 
            if (exam != null) {
                session.delete(exam); // Delete the exam object from the database
            }
            transaction.commit(); // Commit the transaction to persist changes
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
