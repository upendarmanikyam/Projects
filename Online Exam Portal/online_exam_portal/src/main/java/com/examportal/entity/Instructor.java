package com.examportal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Entity class representing an Instructor in the exam portal.
 */
@Entity
public class Instructor {

    // Primary key with auto-generated ID for each instructor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long instructor_Id;

    // Name of the instructor with validation for non-blank input and size constraints
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    // Email of the instructor with validation for proper email format
    @NotBlank
    @Email
    private String email;

    // Department of the instructor with validation for non-blank input and size constraints
    @NotBlank
    @Size(min = 3, max = 50)
    private String department;

    // Password for instructor login with validation for non-blank input
    @NotBlank
    private String password;

    // One-to-many relationship between Instructor and Exam entities
    @OneToMany(mappedBy = "instructor") // Mapped by "instructor" in Exam entity to establish bidirectional relation
    private List<Exam> exams = new ArrayList<>();

    // Default constructor
    public Instructor() {}

    // Parameterized constructor to initialize the instructor details
    public Instructor(String name, String email, String department, String password) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.password = password;
    }

    // Getter and Setter for instructor_Id
    public long getInstructor_Id() {
        return instructor_Id;
    }

    public void setInstructor_Id(long instructor_Id) {
        this.instructor_Id = instructor_Id;
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

    // Getter and Setter for department
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Getter and Setter for exams
    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
