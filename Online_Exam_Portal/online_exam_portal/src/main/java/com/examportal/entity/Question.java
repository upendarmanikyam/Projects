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

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long question_Id;

    @NotBlank
    @Size(min = 10, max = 500)
    private String questionContent;

    @NotNull
    private String questionType;

    @ManyToOne
    @JoinColumn(name = "exam_Id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "course_Id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "category_Id")
    private Category category;

    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();

    
    @ManyToOne
    @JoinColumn(name = "correct_answer_Id") 
    private Answer correctAnswer;
    
    public Question() {}

    public Question(String questionContent, String questionType) {
        this.questionContent = questionContent;
        this.questionType = questionType; }
       

    public long getQuestion_Id() {
        return question_Id;
    }

    public void setQuestionId(long question_Id) {
        this.question_Id = question_Id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

	public void addOption(String option) {
		
	}

	public List<Answer> getOptions() {
		return answers;
	}

	public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer; 
    }

	 public Answer getCorrectAnswer() {
	        return correctAnswer;
	    }
	public void setOptions(List<String> options) {
		
	}
	public String getCorrectAnswerText() {
	    if (correctAnswer != null) {
	        return correctAnswer.getAnswerText();  
	    }
	    return null;  
	}

	}

	

