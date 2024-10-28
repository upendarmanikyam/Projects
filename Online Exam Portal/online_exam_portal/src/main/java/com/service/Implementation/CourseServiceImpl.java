package com.service.Implementation;

import java.util.List;
import com.Dao.Implementation.CourseDaoImpl;
import com.examportal.entity.Course;
import com.services.CourseService;

public class CourseServiceImpl implements CourseService {
    // DAO instance for interacting with Course data
    private CourseDaoImpl courseDao;

    // Constructor initializes the CourseDaoImpl instance
    public CourseServiceImpl() {
        this.courseDao = new CourseDaoImpl();
    }

    @Override
    public void saveCourse(Course course, long instructor_Id) {
        // Save the provided course to the database along with the instructor ID
        courseDao.save(course, instructor_Id);
        System.out.println("Course saved successfully!");
    }

    @Override
    public List<Course> getAllCourses() {
        // Retrieve and return a list of all courses from the database
        return courseDao.getAll();
    }

    @Override
    public void updateCourse(Course course) {
        // Update the provided course details in the database
        courseDao.updateCourse(course);
        System.out.println("Course updated successfully!");
    }

    @Override
    public void deleteCourse(long course_Id) {
        // Delete the course by its ID from the database
        courseDao.delete(course_Id);
        System.out.println("Course deleted successfully!");
    }

    @Override
    public Course getCourseBy_Id(long course_Id) {
        // Retrieve and return the course by its ID
        return courseDao.get(course_Id);
    }

    @Override
    public Course createCourse(Course course, long instructor_Id) {
        // Save the course and associate it with the instructor ID, then return the course
        courseDao.save(course, instructor_Id);
        return course; 
    }
}
