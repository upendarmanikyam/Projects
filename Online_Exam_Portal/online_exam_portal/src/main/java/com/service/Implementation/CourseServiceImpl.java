package com.service.Implementation;

import java.util.List;
import com.Dao.Implementation.CourseDaoImpl;
import com.examportal.entity.Course;
import com.services.CourseService;

public class CourseServiceImpl implements CourseService {
    private CourseDaoImpl courseDao;

    public CourseServiceImpl() {
        this.courseDao = new CourseDaoImpl();
    }

    @Override
    public void saveCourse(Course course, long instructor_Id) {
        courseDao.save(course, instructor_Id);
        System.out.println("Course saved successfully!");
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAll();
    }

    @Override
    public void updateCourse(Course course) {
        courseDao.updateCourse(course);
        System.out.println("Course updated successfully!");
    }

    @Override
    public void deleteCourse(long course_Id) {
        courseDao.delete(course_Id);
        System.out.println("Course deleted successfully!");
    }

    @Override
    public Course getCourseBy_Id(long course_Id) {
        return courseDao.get(course_Id);
    }

    @Override
    public Course createCourse(Course course, long instructor_Id) {
        courseDao.save(course, instructor_Id); 
        return course; 
    }
}
