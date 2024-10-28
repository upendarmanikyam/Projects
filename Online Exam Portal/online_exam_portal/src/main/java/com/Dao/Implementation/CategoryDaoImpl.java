package com.Dao.Implementation;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.examportal.Dao.CategoryDao;
import com.examportal.entity.Category;

public class CategoryDaoImpl implements CategoryDao {
    private SessionFactory sessionFactory;

    // Constructor initializes the SessionFactory
    public CategoryDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Create a new category
    @SuppressWarnings("deprecation")
    @Override
    public void createCategory(Category category) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
            System.out.println("Category created successfully: " + category.getCategoryName());
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error occurred while creating category: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Save a category (similar to createCategory)
    @SuppressWarnings("deprecation")
    @Override
    public void save(Category category) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
            System.out.println("Category saved successfully: " + category.getCategoryName());
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error occurred while saving the category: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieve a category by its ID
    @Override
    public Category get(long category_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Category.class, category_Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update a category (currently not implemented)
    @Override
    public void update(Category category) {
        // Implementation needed
    }

    // Delete a category by ID (currently not implemented)
    @Override
    public void delete(long category_Id) {
        // Implementation needed
    }

    // Retrieve a list of all categories
    @Override
    public List<Category> getAll() {
        List<Category> categories = null;
        try (Session session = sessionFactory.openSession()) {
            categories = session.createQuery("FROM Category", Category.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories; 
    }
}
