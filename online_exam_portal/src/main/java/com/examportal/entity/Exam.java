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

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exam_Id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String subject;

    @NotNull
    @Future
    private LocalDate examDate;

    @ManyToOne
    @JoinColumn(name = "course_Id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_Id")
    private Instructor instructor;

    @OneToMany(mappedBy = "exam")
    private List<Question> questions = new ArrayList<>();

    public Exam() {}

    public Exam(String subject, LocalDate examDate) {
        this.subject = subject;
        this.examDate = examDate;
    }

    public long getExam_Id() {
        return exam_Id;
    }

    public void setExam_Id(long exam_Id) {
        this.exam_Id = exam_Id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
