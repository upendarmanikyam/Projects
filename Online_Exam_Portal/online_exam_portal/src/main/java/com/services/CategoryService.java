package com.services;

import java.util.List;
import com.examportal.entity.Category;

public interface CategoryService {
    Category createCategory(Category category);
    void saveCategory(Category category);
    Category getCategoryBy_Id(long category_Id);
    void updateCategory(Category category);
    void deleteCategory(long categoryId);
    static List<Category> getAllCategories() {
		return null;
		
	} 
}
