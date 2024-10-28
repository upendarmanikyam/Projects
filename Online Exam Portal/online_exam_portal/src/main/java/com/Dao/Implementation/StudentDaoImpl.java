package com.Dao.Implementation;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import com.examportal.Dao.StudentDao;
import com.examportal.entity.Exam;
import com.examportal.entity.Student;

public class StudentDaoImpl implements StudentDao {
    private SessionFactory sessionFactory;

    // Constructor initializes the SessionFactory
    public StudentDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Save a new student to the database
    @SuppressWarnings("deprecation")
    @Override
    public void save(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Find a student by email
    @Override
    public Student findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("FROM Student WHERE email = :email", Student.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create a new student
    @SuppressWarnings("deprecation")
    @Override
    public Student createStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    // Handle student login
    @Override
    public Student login(String name, String email, String password) {
        Student student = findByEmail(email);
        // Check if student exists and password matches
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null; 
    }

    // Get a student by their ID
    @Override
    public Student getStudent(long student_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, student_Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get a list of all students
    @Override
    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update a student's information
    @SuppressWarnings("deprecation")
    @Override
    public Student updateStudent(long student_Id, Student updatedStudent) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, student_Id);
            if (student != null) {
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                student.setPassword(updatedStudent.getPassword());
                session.update(student);
                transaction.commit();
                return student;
            }
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    // Delete a student by ID
    @SuppressWarnings("deprecation")
    @Override
    public String deleteStudent(long student_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, student_Id);
            if (student != null) {
                session.delete(student);
                transaction.commit();
                return "Student deleted successfully.";
            }
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return "Error deleting student.";
    }

    // Get students enrolled in a specific course (currently not implemented)
    @Override
    public List<Student> getStudentsByCourse_Id(long course_Id) {
        return null;
    }

    // Register a student for an exam
    @SuppressWarnings("deprecation")
    @Override
    public Student registerForExam(long student_Id, long course_Id, Exam exam) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            Student student = session.get(Student.class, student_Id);
            if (student != null) {
                student.getExams().add(exam);
                session.saveOrUpdate(student);
                transaction.commit();
            }
            return student;
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
