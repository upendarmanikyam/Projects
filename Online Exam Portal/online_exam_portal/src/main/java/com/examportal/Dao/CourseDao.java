package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Course;

public interface CourseDao {
    // Creates a new course associated with a specific instructor.
    void createCourse(Course course, long instructor_Id);
    
    // Saves a course to the database, associating it with a specific instructor.
    void save(Course course, long instructor_Id);
    
    // Retrieves a course by its ID.
    Course get(long course_Id);
    
    // Deletes a course by its ID.
    void delete(long course_Id);
    
    // Retrieves a list of all courses.
    List<Course> getAll();
    
    // Updates the details of an existing course.
    void updateCourse(Course course);
}
