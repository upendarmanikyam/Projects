package com.examportal.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Entity class representing an Exam in the exam portal.
 */
@Entity
public class Exam {

    // Primary key with auto-generated ID for each exam
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exam_Id;

    // Subject of the exam with validation for non-blank input and size constraints
    @NotBlank
    @Size(min = 3, max = 50)
    private String subject;

    // Exam date with validation to ensure it's a future date
    @NotNull
    @Future
    private LocalDate examDate;

    // Many-to-one relationship with the Course entity
    @ManyToOne
    @JoinColumn(name = "course_Id") // Foreign key column in the Exam table for Course
    private Course course;

    // Many-to-one relationship with the Instructor entity
    @ManyToOne
    @JoinColumn(name = "instructor_Id") // Foreign key column in the Exam table for Instructor
    private Instructor instructor;

    // One-to-many relationship with the Question entity
    @OneToMany(mappedBy = "exam") // Mapped by "exam" in Question entity to establish bidirectional relation
    private List<Question> questions = new ArrayList<>();

    // Default constructor
    public Exam() {}

    // Parameterized constructor to initialize the exam details
    public Exam(String subject, LocalDate examDate) {
        this.subject = subject;
        this.examDate = examDate;
    }

    // Getter and Setter for exam_Id
    public long getExam_Id() {
        return exam_Id;
    }

    public void setExam_Id(long exam_Id) {
        this.exam_Id = exam_Id;
    }

    // Getter and Setter for subject
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Getter and Setter for examDate
    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    // Getter and Setter for course
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
}
