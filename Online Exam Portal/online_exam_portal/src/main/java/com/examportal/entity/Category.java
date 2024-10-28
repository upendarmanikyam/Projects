package com.examportal.entity;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity // Specifies that this class is an entity and will be mapped to a table in the database.
public class Category {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the primary key will be generated automatically.
    private long category_Id;

    @NotBlank(message = "Category name cannot be blank.") // Ensures the category name is not empty or null.
    @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters.") // Specifies the valid length for the category name.
    private String categoryName;

    @OneToMany(mappedBy = "category") // Defines a one-to-many relationship with the Question entity, referenced by the "category" field in Question.
    private List<Question> questions = new ArrayList<>(); // Initializes the list to avoid null pointer exceptions.

    // Default constructor
    public Category() {}

    // Parameterized constructor for initializing category with provided values
    public Category(long category_Id, String categoryName) {
        this.category_Id = category_Id;
        this.categoryName = categoryName;
    }

    // Getter and setter for category_Id
    public long getCategory_Id() {
        return category_Id;
    }

    public void setCategory_Id(long category_Id) {
        this.category_Id = category_Id;
    }

    // Getter and setter for categoryName
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Getter and setter for questions
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
