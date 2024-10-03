package com.Dao.Implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.examportal.Dao.InstructorDao;
import com.examportal.entity.Exam;
import com.examportal.entity.Instructor;

public class InstructorDaoImpl implements InstructorDao {
    private SessionFactory sessionFactory;

    public InstructorDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @SuppressWarnings("deprecation")
	@Override
    public void save(Instructor instructor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(instructor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Instructor get(long instructor_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Instructor.class, instructor_Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void update(Instructor instructor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(instructor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public void delete(long instructor_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructor_Id);
            if (instructor != null) {
                session.delete(instructor);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Instructor> getAllInstructors() {
        try (Session session = sessionFactory.openSession()) {
            Query<Instructor> query = session.createQuery("FROM Instructor", Instructor.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void conductExam(long exam_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, exam_Id);
            if (exam != null) {
                // Logic to conduct the exam
                // (e.g., updating exam status, notifying students, etc.)
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public void deleteExam(long exam_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, exam_Id);
            if (exam != null) {
                session.delete(exam);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    } @Override
    public Instructor findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Instructor> query = session.createQuery("FROM Instructor WHERE email = :email", Instructor.class);
            query.setParameter("email", email);
            return query.uniqueResult(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }

    @SuppressWarnings("deprecation")
	@Override
    public Instructor createInstructor(Instructor instructor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(instructor); 
            transaction.commit();
            return instructor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    public Instructor login(String name, String email, String password,String department) {
        Instructor instructor = findByEmail(email); 
        if (instructor != null && instructor.getPassword().equals(password)) {
            return instructor; 
        }
        return null;
    }

	@Override
	public List<Exam> getExamsByInstructor(long instructor_Id) {
		return null;
	}

    }

