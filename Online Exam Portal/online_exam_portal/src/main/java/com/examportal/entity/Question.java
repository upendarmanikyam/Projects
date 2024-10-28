package com.examportal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Entity class representing a Question in the exam portal.
 */
@Entity
public class Question {

    // Primary key with auto-generated ID for each question
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long question_Id;

    // Content of the question with validation for minimum and maximum length
    @NotBlank
    @Size(min = 10, max = 500)
    private String questionContent;

    // Type of the question (e.g., multiple choice, true/false) with validation
    @NotNull
    private String questionType;

    // Many-to-one relationship between Question and Exam entities
    @ManyToOne
    @JoinColumn(name = "exam_Id") // Foreign key column linking question to an exam
    private Exam exam;

    // Many-to-one relationship between Question and Course entities
    @ManyToOne
    @JoinColumn(name = "course_Id") // Foreign key column linking question to a course
    private Course course;

    // Many-to-one relationship between Question and Category entities
    @ManyToOne
    @JoinColumn(name = "category_Id") // Foreign key column linking question to a category
    private Category category;

    // One-to-many relationship between Question and Answer entities, with eager fetching and cascading
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();

    // Many-to-one relationship to represent the correct answer for a question
    @ManyToOne
    @JoinColumn(name = "correct_answer_Id") // Foreign key column linking to the correct answer
    private Answer correctAnswer;

    // Default constructor
    public Question() {}

    // Parameterized constructor to initialize question content and type
    public Question(String questionContent, String questionType) {
        this.questionContent = questionContent;
        this.questionType = questionType;
    }

    // Getter and Setter for question_Id
    public long getQuestion_Id() {
        return question_Id;
    }

    public void setQuestionId(long question_Id) {
        this.question_Id = question_Id;
    }

    // Getter and Setter for questionContent
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    // Getter and Setter for questionType
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    // Getter and Setter for exam
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    // Getter and Setter for course
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // Getter and Setter for category
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Getter and Setter for answers
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    // Method to add an option (answer) for the question
    public void addOption(String option) {
        // Implementation not provided
    }

    // Getter for options (answers) related to the question
    public List<Answer> getOptions() {
        return answers;
    }

    // Setter for correctAnswer
    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    // Getter for correctAnswer
    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    // Method to set options with a list of strings (placeholders)
    public void setOptions(List<String> options) {
    }

    // Method to retrieve the text of the correct answer if available
    public String getCorrectAnswerText() {
        if (correctAnswer != null) {
            return correctAnswer.getAnswerText();
        }
        return null;
    }
}
