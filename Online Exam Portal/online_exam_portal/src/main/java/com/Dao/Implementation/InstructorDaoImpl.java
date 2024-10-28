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

    // Constructor initializes the SessionFactory
    public InstructorDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Saves an instructor to the database
    @SuppressWarnings("deprecation")
    @Override
    public void save(Instructor instructor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(instructor); // Save the instructor entity
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        }
    }

    // Retrieves an instructor by their ID
    @Override
    public Instructor get(long instructor_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Instructor.class, instructor_Id); // Get instructor by ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if not found or an error occurs
    }

    // Updates an existing instructor's details
    @SuppressWarnings("deprecation")
    @Override
    public void update(Instructor instructor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(instructor); // Update the instructor entity
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        }
    }

    // Deletes an instructor by their ID
    @SuppressWarnings("deprecation")
    @Override
    public void delete(long instructor_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructor_Id); // Get the instructor to delete
            if (instructor != null) {
                session.delete(instructor); // Delete the instructor entity
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        }
    }

    // Retrieves a list of all instructors
    @Override
    public List<Instructor> getAllInstructors() {
        try (Session session = sessionFactory.openSession()) {
            Query<Instructor> query = session.createQuery("FROM Instructor", Instructor.class); // Create a query to get all instructors
            return query.list(); // Return the list of instructors
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if an error occurs
    }

    // Conducts an exam based on the exam ID
    @Override
    public void conductExam(long exam_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, exam_Id); // Get the exam to conduct
            if (exam != null) {
                // Logic to conduct the exam
                // (e.g., updating exam status, notifying students, etc.)
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        }
    }

    // Deletes an exam by its ID
    @SuppressWarnings("deprecation")
    @Override
    public void deleteExam(long exam_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Exam exam = session.get(Exam.class, exam_Id); // Get the exam to delete
            if (exam != null) {
                session.delete(exam); // Delete the exam entity
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        }
    }

    // Finds an instructor by their email
    @Override
    public Instructor findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Instructor> query = session.createQuery("FROM Instructor WHERE email = :email", Instructor.class);
            query.setParameter("email", email); // Set the email parameter for the query
            return query.uniqueResult(); // Return the unique instructor found
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if not found or an error occurs
    }

    // Creates a new instructor in the database
    @SuppressWarnings("deprecation")
    @Override
    public Instructor createInstructor(Instructor instructor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(instructor); // Save the new instructor
            transaction.commit();
            return instructor; // Return the created instructor
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        }
        return null; // Return null if an error occurs
    }

    // Handles instructor login
    @Override
    public Instructor login(String name, String email, String password, String department) {
        Instructor instructor = findByEmail(email); // Find instructor by email
        if (instructor != null && instructor.getPassword().equals(password)) {
            return instructor; // Return the instructor if the password matches
        }
        return null; // Return null if not found or password does not match
    }

    // Retrieves a list of exams for a specific instructor (currently unimplemented)
    @Override
    public List<Exam> getExamsByInstructor(long instructor_Id) {
        return null; // Implement logic to get exams by instructor ID
    }
}
