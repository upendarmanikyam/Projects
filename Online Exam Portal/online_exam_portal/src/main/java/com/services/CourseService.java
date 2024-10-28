package com.services;

import java.util.List;
import com.examportal.entity.Course;

public interface CourseService {
    // Create a new course associated with a specific instructor
    Course createCourse(Course course, long instructor_Id); 
    
    // Save a course to the database, associating it with an instructor
    void saveCourse(Course course, long instructor_Id); 
    
    // Retrieve a list of all courses available in the system
    List<Course> getAllCourses();
    
    // Update the details of an existing course
    void updateCourse(Course course);
    
    // Delete a course by its ID
    void deleteCourse(long course_Id);
    
    // Retrieve a course by its ID
    Course getCourseBy_Id(long course_Id);
}
