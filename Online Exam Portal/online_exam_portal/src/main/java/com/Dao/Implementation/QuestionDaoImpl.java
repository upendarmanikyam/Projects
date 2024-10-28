package com.Dao.Implementation;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.examportal.Dao.QuestionDao;
import com.examportal.entity.Answer;
import com.examportal.entity.Question;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private SessionFactory sessionFactory;

    // Constructor initializes the SessionFactory
    public QuestionDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Save a new question to the database
    @SuppressWarnings("deprecation")
    @Override
    public void save(Question question) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Save the category if it is new and has a default ID
            if (question.getCategory() != null && question.getCategory().getCategory_Id() == 0) {
                session.save(question.getCategory()); 
            }

            // Save the question itself
            session.save(question);
            System.out.println("Question saved with ID: " + question.getQuestion_Id());

            // Save associated answers if they exist
            if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
                for (Answer answer : question.getAnswers()) {
                    answer.setQuestion(question); // Set the question for each answer
                    session.save(answer); // Save the answer
                    System.out.println("Answer saved: " + answer.getAnswerText());
                }
            } else {
                System.out.println("No answers to save.");
            }

            // Commit the transaction
            transaction.commit();
            System.out.println("Question and associated Answers saved successfully!");
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error occurred while saving question: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get a question by its ID
    @Override
    public Question get(long questionId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Question.class, questionId); // Fetch question by ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if not found
    }

    // Update an existing question
    @SuppressWarnings("deprecation")
    @Override
    public void update(Question question) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(question); // Update the question in the database
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete a question by ID
    @SuppressWarnings("deprecation")
    @Override
    public void delete(long questionId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Question question = session.get(Question.class, questionId); // Fetch question by ID
            if (question != null) {
                session.delete(question); // Delete the question if it exists
            }
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Get a list of all questions
    @Override
    public List<Question> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Question> query = session.createQuery("FROM Question", Question.class);
            return query.list(); // Return list of all questions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if there was an error
    }

    // Get questions associated with a specific exam
    @Override
    public List<Question> getQuestionsForExam(long exam_Id) {
        List<Question> questions = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Fetching questions associated with the exam
            String hql = "FROM Question q WHERE q.exam.exam_Id = :examId";
            Query<Question> query = session.createQuery(hql, Question.class);
            query.setParameter("examId", exam_Id);
            questions = query.list(); // Get the list of questions

            // Manually initializing lazy-loaded answers collection
            for (Question question : questions) {
                Hibernate.initialize(question.getAnswers()); // Ensure answers are loaded
            }

            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return questions; // Return the list of questions for the exam
    }
}
