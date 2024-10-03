package com.services;

import java.util.List;
import com.examportal.entity.Course;

public interface CourseService {
    Course createCourse(Course course, long instructor_Id); 
    void saveCourse(Course course, long instructor_Id); 
    List<Course> getAllCourses();
    void updateCourse(Course course);
    void deleteCourse(long course_Id);
    Course getCourseBy_Id(long course_Id);
}
