package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Category;

public interface CategoryDao {
    // Creates a new category in the database.
    void createCategory(Category category);
    
    // Saves a category to the database, either creating a new one or updating an existing one.
    void save(Category category);
    
    // Retrieves a category by its ID.
    Category get(long category_Id);
    
    // Updates the details of an existing category.
    void update(Category category);
    
    // Deletes a category by its ID.
    void delete(long category_Id);
    
    // Retrieves a list of all categories.
    List<Category> getAll();
}
