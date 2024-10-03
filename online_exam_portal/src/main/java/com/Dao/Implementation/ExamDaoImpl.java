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

    public ExamDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @SuppressWarnings("deprecation")
	@Override
    public void save(Exam exam) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            session.save(exam); 
            transaction.commit(); 
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }

    @Override
    public Exam get(long exam_Id) {
        Exam exam = null;
        try (Session session = sessionFactory.openSession()) {
            exam = session.get(Exam.class, exam_Id);
            if (exam != null) {
                Hibernate.initialize(exam.getQuestions());
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return exam; 
    }


    @Override
    public List<Exam> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Exam", Exam.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void update(Exam exam) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            session.update(exam); 
            transaction.commit(); 
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public void delete(long exam_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            Exam exam = session.get(Exam.class, exam_Id); 
            if (exam != null) {
                session.delete(exam); 
            }
            transaction.commit(); 
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }
}
