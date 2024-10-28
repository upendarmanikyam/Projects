package com.examportal.entity;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Entity class representing a Course in the exam portal.
 */
@Entity
public class Course {

    // Primary key with auto-generated ID for each course
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long course_Id;

    // Name of the course with validation for non-blank input and size constraints
    @NotBlank
    @Size(min = 3, max = 50)
    private String courseName;

    // Many-to-one relationship with the Instructor entity
    @ManyToOne
    @JoinColumn(name = "instructor_Id") // Foreign key column in the Course table for Instructor
    private Instructor instructor;

    // One-to-many relationship with the Question entity
    @OneToMany(mappedBy = "course") // Mapped by "course" in Question entity to establish bidirectional relation
    private List<Question> questions = new ArrayList<>();

    // Default constructor
    public Course() {}

    // Parameterized constructor to initialize the course name
    public Course(String courseName) {
        this.courseName = courseName;
    }

    // Getter and Setter for course_Id
    public long getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(long course_Id) {
        this.course_Id = course_Id;
    }

    // Getter and Setter for courseName
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Getter and Setter for instructor
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // Getter and Setter for questions
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

 // Placeholder method to get an associated exam (currently returns null)
    public Exam getExam() {
        return null; // Return null or a default value if no specific logic is implemented
    }

    // Placeholder method to get the score (currently returns null)
    public String getScore() {
        return null; // Return null or a default value if no specific logic is implemented
    }

}
