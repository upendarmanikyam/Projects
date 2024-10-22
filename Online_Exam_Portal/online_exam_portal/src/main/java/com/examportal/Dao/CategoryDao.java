package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Category;

public interface CategoryDao {
    void createCategory(Category category);
    void save(Category category);
    Category get(long category_Id);
    void update(Category category);
    void delete(long category_Id);
    List<Category> getAll();
}
