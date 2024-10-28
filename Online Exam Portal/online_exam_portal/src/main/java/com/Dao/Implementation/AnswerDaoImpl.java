package com.Dao.Implementation;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query; 
import com.examportal.Dao.AnswerDao;
import com.examportal.entity.Answer;
import com.examportal.entity.Question;

public class AnswerDaoImpl implements AnswerDao {

    private SessionFactory sessionFactory;

    // Constructor to set up Hibernate session factory
    public AnswerDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Save a new answer to the database
    @SuppressWarnings("deprecation")
    public void save(Answer answer) {
        Session session = sessionFactory.openSession(); // Start a new session
        Transaction transaction = null; // Declare a transaction variable
        try {
            transaction = session.beginTransaction(); // Begin transaction
            // Fetch the associated question
            Question question = session.get(Question.class, answer.getQuestion().getQuestion_Id());
            
            if (question != null) { // If the question exists
                answer.setQuestion(question); // Link answer to the question
                session.save(answer); // Save the answer
                question.getAnswers().add(answer); // Add answer to the question's answers list
            } else {
                System.out.println("Question not found."); // Print a message if question doesn't exist
            }
            
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Roll back if there's an error
            e.printStackTrace(); // Print error details
        } finally {
            session.close(); // Close the session
        }
    }

    // Get an answer by its ID
    @Override
    public Answer get(long answer_Id) {
        try (Session session = sessionFactory.openSession()) { // Open a session
            return session.get(Answer.class, answer_Id); // Fetch and return the answer
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
        return null; // Return null if not found
    }

    // Update an existing answer
    @SuppressWarnings("deprecation")
    @Override
    public void update(Answer answer) {
        Transaction transaction = null; // Declare a transaction variable
        try (Session session = sessionFactory.openSession()) { // Open a session
            transaction = session.beginTransaction(); // Begin transaction
            session.update(answer); // Update the answer
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Roll back on error
            e.printStackTrace(); // Print error details
        }
    }

    // Delete an answer by its ID
    @SuppressWarnings("deprecation")
    @Override
    public void delete(long answer_Id) {
        Transaction transaction = null; // Declare a transaction variable
        try (Session session = sessionFactory.openSession()) { // Open a session
            transaction = session.beginTransaction(); // Begin transaction
            Answer answer = session.get(Answer.class, answer_Id); // Get the answer
            if (answer != null) {
                session.delete(answer); // Delete the answer
            }
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Roll back on error
            e.printStackTrace(); // Print error details
        }
    }

    // Get all answers for a specific question
    @Override
    public List<Answer> getAnswersByQuestion_Id(long question_Id) {
        try (Session session = sessionFactory.openSession()) { // Open a session
            Query<Answer> query = session.createQuery("FROM Answer WHERE question.question_Id = :questionId", Answer.class);
            query.setParameter("questionId", question_Id); // Set the parameter for the query
            return query.list(); // Return the list of answers
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
        return null; // Return null if there's an error
    }

    // Get all answers from the database
    @Override
    public List<Answer> getAll() {
        try (Session session = sessionFactory.openSession()) { // Open a session
            Query<Answer> query = session.createQuery("FROM Answer", Answer.class); // Create the query
            return query.list(); // Return the list of answers
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
        return null; // Return null if there's an error
    }
}
