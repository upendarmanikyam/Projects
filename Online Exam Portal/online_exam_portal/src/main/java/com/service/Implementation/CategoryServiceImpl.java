package com.service.Implementation;

import com.Dao.Implementation.CategoryDaoImpl;
import com.examportal.Dao.CategoryDao;
import com.examportal.entity.Category;
import com.services.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    // DAO instance for category operations
    private CategoryDao categoryDao = new CategoryDaoImpl();

    // Default constructor
    public CategoryServiceImpl() {
    }

    @Override
    public void saveCategory(Category category) {
        // Save the category to the database
        categoryDao.save(category);
        System.out.println("Category saved successfully!");
    }

    @Override
    public Category createCategory(Category category) {
        // Check if the category already exists by its ID
        if (categoryDao.get(category.getCategory_Id()) != null) {
            System.out.println("Category already exists: " + category.getCategoryName());
            return category; // Return the existing category
        }

        // Save the new category to the database
        categoryDao.save(category);
        System.out.println("Category created: " + category.getCategoryName());
        return category; // Return the newly created category
    }

    @Override
    public void updateCategory(Category category) {
        // Update the category in the database
        categoryDao.update(category);
        System.out.println("Category updated successfully!");
    }

    @Override
    public void deleteCategory(long category_Id) {
        // Delete the category from the database by its ID
        categoryDao.delete(category_Id);
        System.out.println("Category deleted successfully!");
    }
    
    public List<Category> getAllCategories() {
        // Retrieve and return all categories from the database
        return categoryDao.getAll();
    }

    @Override
    public Category getCategoryBy_Id(long category_Id) {
        // Retrieve and return a specific category by its ID
        return categoryDao.get(category_Id);
    }
}
