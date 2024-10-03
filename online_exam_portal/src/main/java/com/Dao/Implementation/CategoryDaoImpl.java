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

    public CategoryDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @SuppressWarnings("deprecation")
	@Override
    public void createCategory(Category category) {
    	Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    

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
            if (transaction != null) {
                transaction.rollback();
            }
            
            System.out.println("Error occurred while saving the category: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Category get(long category_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Category.class, category_Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Category category) {
    }

    @Override
    public void delete(long category_Id) {
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = null;
        try (Session session = sessionFactory.openSession()) {
            categories = session.createQuery("from Category", Category.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories; 
    }

}
