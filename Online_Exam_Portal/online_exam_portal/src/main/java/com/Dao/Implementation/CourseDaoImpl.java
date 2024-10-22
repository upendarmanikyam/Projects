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

    public CourseDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @SuppressWarnings("deprecation")
	@Override
    public void save(Course course, long instructor_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructor_Id);
            if (instructor != null) {
                course.setInstructor(instructor);
                session.save(course); 
                transaction.commit();
                System.out.println("Course saved successfully!");
            } else {
                throw new IllegalArgumentException("Instructor not found with ID: " + instructor_Id);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Transaction rolled back due to: " + e.getMessage());
            }
        }
    }

    @Override
    public Course get(long course_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, course_Id);
        } catch (Exception e) {
            System.err.println("Error retrieving course: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void delete(long course_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, course_Id);
            if (course != null) {
                session.delete(course);
                transaction.commit();
                System.out.println("Course deleted successfully!");
            } else {
                System.err.println("Course not found for ID: " + course_Id);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting course: " + e.getMessage());
        }
    }

    @Override
    public List<Course> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createQuery("FROM Course", Course.class);
            return query.list();
        } catch (Exception e) {
            System.err.println("Error retrieving all courses: " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void updateCourse(Course course) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
            System.out.println("Course updated successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating course: " + e.getMessage());
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public void createCourse(Course course, long instructor_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructor_Id);
            if (instructor != null) {
                course.setInstructor(instructor); 
                session.save(course); 
                transaction.commit();
                System.out.println("Course created successfully!");
            } else {
                throw new IllegalArgumentException("Instructor not found with ID: " + instructor_Id);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Transaction rolled back due to: " + e.getMessage());
            }
        }
    }

}
