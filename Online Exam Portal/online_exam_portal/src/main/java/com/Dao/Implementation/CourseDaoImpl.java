package com.Dao.Implementation;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import com.examportal.Dao.CourseDao;
import com.examportal.entity.Course;
import com.examportal.entity.Instructor;

public class CourseDaoImpl implements CourseDao {
    private SessionFactory sessionFactory;

    // Constructor initializes the SessionFactory
    public CourseDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Save a new course and associate it with an instructor
    @SuppressWarnings("deprecation")
    @Override
    public void save(Course course, long instructor_Id) {
        Transaction transaction = null; // Initialize transaction
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            Instructor instructor = session.get(Instructor.class, instructor_Id); // Fetch instructor
            if (instructor != null) {
                course.setInstructor(instructor); // Set instructor for the course
                session.save(course); // Save the course
                transaction.commit(); // Commit transaction
                System.out.println("Course saved successfully!");
            } else {
                throw new IllegalArgumentException("Instructor not found with ID: " + instructor_Id);
            }
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Transaction rolled back due to: " + e.getMessage());
            }
        }
    }

    // Retrieve a course by its ID
    @Override
    public Course get(long course_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, course_Id); // Return the course object
        } catch (Exception e) {
            System.err.println("Error retrieving course: " + e.getMessage());
        }
        return null; // Return null if not found
    }

    // Delete a course by its ID
    @SuppressWarnings("deprecation")
    @Override
    public void delete(long course_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            Course course = session.get(Course.class, course_Id); // Fetch course
            if (course != null) {
                session.delete(course); // Delete the course
                transaction.commit(); // Commit transaction
                System.out.println("Course deleted successfully!");
            } else {
                System.err.println("Course not found for ID: " + course_Id);
            }
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting course: " + e.getMessage());
        }
    }

    // Retrieve a list of all courses
    @Override
    public List<Course> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createQuery("FROM Course", Course.class); // Create query
            return query.list(); // Return the list of courses
        } catch (Exception e) {
            System.err.println("Error retrieving all courses: " + e.getMessage());
        }
        return null; // Return null if an error occurs
    }

    // Update an existing course
    @SuppressWarnings("deprecation")
    @Override
    public void updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.update(course); // Update the course
            transaction.commit(); // Commit transaction
            System.out.println("Course updated successfully!");
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating course: " + e.getMessage());
        }
    }

    // Create a new course and associate it with an instructor
    @SuppressWarnings("deprecation")
    @Override
    public void createCourse(Course course, long instructor_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            Instructor instructor = session.get(Instructor.class, instructor_Id); // Fetch instructor
            if (instructor != null) {
                course.setInstructor(instructor); // Set instructor for the course
                session.save(course); // Save the course
                transaction.commit(); // Commit transaction
                System.out.println("Course created successfully!");
            } else {
                throw new IllegalArgumentException("Instructor not found with ID: " + instructor_Id);
            }
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Transaction rolled back due to: " + e.getMessage());
            }
        }
    }
}
