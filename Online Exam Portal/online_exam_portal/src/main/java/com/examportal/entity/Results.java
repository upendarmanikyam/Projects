package com.examportal.entity;

import javax.validation.constraints.Positive;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entity class representing the Results of a student's exam in the exam portal.
 */
@Entity
public class Results {

    // Primary key with auto-generated ID for each result entry
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long results_Id;

    // Score of the student in the exam with validation to ensure positive values
    @Positive(message = "Score must be a positive number")
    private long score;

    // Many-to-one relationship between Results and Student entities
    @ManyToOne
    @JoinColumn(name = "student_Id")  // Foreign key column to link results to a student
    private Student student;

    // Many-to-one relationship between Results and Exam entities
    @ManyToOne
    @JoinColumn(name = "exam_Id")  // Foreign key column to link results to an exam
    private Exam exam;

    // Default constructor
    public Results() {}

    // Parameterized constructor to initialize results with score, student, and exam
    public Results(long score, int grade, Student student_Id, Exam exam) {
        this.score = score;
        this.student = student_Id;
        this.exam = exam;
    }

    // Getter and Setter for results_Id
    public long getResults_Id() {
        return results_Id;
    }

    public void setResults_Id(long results_Id) {
        this.results_Id = results_Id;
    }

    // Getter and Setter for score
    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    // Getter and Setter for student
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student_Id) {
        this.student = student_Id;
    }

    // Getter and Setter for exam
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    // Placeholder method to set total questions if needed
    public void setTotalQuestions(int size) {
        
    }

    // Placeholder method to get exam ID if needed
    public long getExam_Id() {
        return 0;
    }
}
