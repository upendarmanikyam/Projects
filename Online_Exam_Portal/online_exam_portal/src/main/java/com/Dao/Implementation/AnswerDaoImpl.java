package com.Dao.Implementation;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query; // Import the correct Query class
import com.examportal.Dao.AnswerDao;
import com.examportal.entity.Answer;
import com.examportal.entity.Question;
public class AnswerDaoImpl implements AnswerDao {

    private SessionFactory sessionFactory;

    public AnswerDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

  
    @SuppressWarnings("deprecation")
    public void save(Answer answer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Question question = session.get(Question.class, answer.getQuestion().getQuestion_Id());
            
            if (question != null) {
                System.out.println("Question fetched: " + question.getQuestionContent());
                answer.setQuestion(question);
                session.save(answer);
                question.getAnswers().add(answer);
            } else {
                System.out.println("Question not found.");
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    


    @Override
    public Answer get(long answer_Id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Answer.class, answer_Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void update(Answer answer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(answer); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public void delete(long answer_Id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Answer answer = session.get(Answer.class, answer_Id);
            if (answer != null) {
                session.delete(answer); 
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Answer> getAnswersByQuestion_Id(long question_Id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Answer> query = session.createQuery("FROM Answer WHERE question.question_Id = :questionId", Answer.class);
            query.setParameter("questionId", question_Id);
            return query.list(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    public List<Answer> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Answer> query = session.createQuery("FROM Answer", Answer.class);
            return query.list(); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }
}
