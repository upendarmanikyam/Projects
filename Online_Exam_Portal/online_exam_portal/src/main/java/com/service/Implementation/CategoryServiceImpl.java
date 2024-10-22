package com.service.Implementation;

import com.Dao.Implementation.CategoryDaoImpl;
import com.examportal.Dao.CategoryDao;
import com.examportal.entity.Category;
import com.services.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    public CategoryServiceImpl() {
    }

    @Override
    public void saveCategory(Category category) {
        categoryDao.save(category);
        System.out.println("Category saved successfully!");
    }

    @Override
    public Category createCategory(Category category) {
        if (categoryDao.get(category.getCategory_Id()) != null) {
            System.out.println("Category already exists: " + category.getCategoryName());
            return category;
        }

        categoryDao.save(category);
        System.out.println("Category created: " + category.getCategoryName());
        return category;
    }
    

    @Override
    public void updateCategory(Category category) {
        categoryDao.update(category);
        System.out.println("Category updated successfully!");
    }

    @Override
    public void deleteCategory(long category_Id) {
        categoryDao.delete(category_Id);
        System.out.println("Category deleted successfully!");
    }
    public List<Category> getAllCategories() {
        return categoryDao.getAll();
    }

    @Override
    public Category getCategoryBy_Id(long category_Id) {
        return categoryDao.get(category_Id);
    }


}
