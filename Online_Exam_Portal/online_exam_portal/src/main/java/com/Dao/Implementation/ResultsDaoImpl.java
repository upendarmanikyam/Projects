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

    public ResultsDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @SuppressWarnings("deprecation")
	@Override
    public void save(Results result) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            session.save(result); 
            transaction.commit(); 
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }

    @Override
    public Results get(long results_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Results.class, results_Id);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }

    @SuppressWarnings("deprecation")
	@Override
    public void update(Results result) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            session.update(result); 
            transaction.commit(); 
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public void delete(long results_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); 
            Results results = session.get(Results.class, results_Id);
            if (results != null) {
                session.delete(results); 
            }
            transaction.commit(); 
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); 
            e.printStackTrace(); 
        }
    }

    @Override
    public List<Results> getAllResults() {
        try (Session session = sessionFactory.openSession()) {
            Query<Results> query = session.createQuery("FROM Results", Results.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }
    @Override
    public void submitExam(Student student, Exam exam, int score) {
        Results result = new Results();
        result.setStudent(student);
        result.setExam(exam);
        result.setScore(score);
        save(result); // Call the save method
    }
}
