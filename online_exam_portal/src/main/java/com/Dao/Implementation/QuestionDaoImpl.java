package com.Dao.Implementation;

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

    public QuestionDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @SuppressWarnings("deprecation")
	@Override
    public void save(Question question) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (question.getCategory() != null && question.getCategory().getCategory_Id() == 0) {
                session.save(question.getCategory()); 
            }
            session.save(question);
            System.out.println("Question saved with ID: " + question.getQuestion_Id());
            if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
                for (Answer answer : question.getAnswers()) {
                    answer.setQuestion(question); 
                    session.save(answer); 
                    System.out.println("Answer saved: " + answer.getAnswerText());
                }
            } else {
                System.out.println("No answers to save.");
            }

            transaction.commit();
            System.out.println("Question and associated Answers saved successfully!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error occurred while saving question: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public Question get(long questionId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Question.class, questionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void update(Question question) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(question); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public void delete(long questionId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Question question = session.get(Question.class, questionId);
            if (question != null) {
                session.delete(question); 
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Question> query = session.createQuery("FROM Question", Question.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
