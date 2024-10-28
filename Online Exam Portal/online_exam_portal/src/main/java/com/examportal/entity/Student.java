package com.examportal.entity;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

/**
 * Entity class representing a Student in the exam portal.
 */
@Entity
public class Student {

    // Primary key with auto-generated ID for each student
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_Id")
    private long student_Id;

    // Student's name with validation constraints
    @NotBlank(message = "Please enter your name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    // Student's email with validation constraints for proper format
    @NotBlank(message = "Please enter a valid email")
    @Email(message = "Invalid email format")
    private String email;

    // Student's password with validation for length and special character requirement
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = ".*[!@#$%^&*()_+].*", message = "Password must contain at least one special character")
    private String password;

    // Many-to-many relationship mapping between Student and Exam entities
    @ManyToMany
    @JoinTable(
        name = "registration",
        joinColumns = @JoinColumn(name = "student_Id"),
        inverseJoinColumns = @JoinColumn(name = "exam_Id")
    )
    private Set<Exam> exams = new HashSet<>();

    // Default constructor
    public Student() {}

    // Parameterized constructor to initialize a student with name, email, and password
    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter and Setter for student_Id
    public long getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(long student_Id) {
        this.student_Id = student_Id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for exams set
    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }
}
