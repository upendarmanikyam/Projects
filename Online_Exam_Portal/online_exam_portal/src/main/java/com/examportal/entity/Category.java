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

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_Id;

    @NotBlank(message = "Category name cannot be blank.")
    @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters.")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Question> questions = new ArrayList<>();

    public Category() {}

    public Category(long category_Id, String categoryName) {
        this.category_Id = category_Id;
        this.categoryName = categoryName;
    }

    public long getCategory_Id() {
        return category_Id;
    }

    public void setCategory_Id(long category_Id) {
        this.category_Id = category_Id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
