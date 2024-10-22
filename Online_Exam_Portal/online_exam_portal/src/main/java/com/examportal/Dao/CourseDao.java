package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Course;

public interface CourseDao {
    void createCourse(Course course ,long instructor_Id);
    void save(Course course, long instructor_Id);
    Course get(long course_Id);
    void delete(long course_Id);
    List<Course> getAll();
	void updateCourse(Course course);
	
}

