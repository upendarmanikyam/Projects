package com.services;

import java.util.List;
import com.examportal.entity.Category;

public interface CategoryService {
    // Create a new category
    Category createCategory(Category category);
    
    // Save a category to the database
    void saveCategory(Category category);
    
    // Retrieve a category by its ID
    Category getCategoryBy_Id(long category_Id);
    
    // Update the details of an existing category
    void updateCategory(Category category);
    
    // Delete a category by its ID
    void deleteCategory(long categoryId);
    
    // Retrieve a list of all categories (static method)
    static List<Category> getAllCategories() {
        return null; // Placeholder implementation
    }
}
