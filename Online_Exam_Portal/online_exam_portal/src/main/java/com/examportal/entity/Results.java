package com.examportal.entity;
import javax.validation.constraints.Positive;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long results_Id;

    @Positive(message = "Score must be a positive number")
    private long score;

    /*@Min(value = 0, message = "Grade cannot be less than 0")
    @Positive(message = "Grade must be a positive integer")
    private int grade;*/
    
    @ManyToOne
    @JoinColumn(name = "student_Id")  
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "exam_Id")
    private Exam exam;

    public Results() {}

    public Results(long score, int grade, Student student_Id, Exam exam) {
        this.score = score;
   /*     this.grade  = grade ; */
        this.student = student_Id;
        this.exam = exam;
    }

    public long getResults_Id() {
        return results_Id;
    }

    public void setResults_Id(long results_Id) {
        this.results_Id = results_Id;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student_Id) {
        this.student = student_Id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

	public void setTotalQuestions(int size) {
		
	}

	public long getExam_Id() {
		return 0;
	}
}
