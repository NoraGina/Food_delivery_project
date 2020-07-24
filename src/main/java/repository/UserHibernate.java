package repository;

import model.Department;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class UserHibernate implements UserDao {

    @Override
    public void createUser(User user) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            session.close();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Set<User> readAllUsers() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            return new HashSet<>(session.createQuery("from User", User.class).list());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findUserById(Long id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            User user = session.find(User.class, id );
            session.close();
            return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(findUserById(id));
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            session.close();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByFirstNameAndLastName(String firstName, String lastName) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query= session. createQuery("from User where first_name=:firstName and last_name=:lastName");
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            User user = (User) query.uniqueResult();
            session.close();
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findUserByUserName(String userName) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query= session. createQuery("from User where userName=:userName");
            query.setParameter("userName", userName);
            User user = (User) query.uniqueResult();
            session.close();
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long isUserExist(String userName, String password) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("Select COUNT(u) " +
                    "from User u where u.userName=:userName and u.password=:password ");
            query.setParameter("userName", userName);
            query.setParameter("password", password);
            Long result = (Long) query.getSingleResult();
            session.close();
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long isAdministratorExist(String userName, String password, Department department) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("Select COUNT(u) from User u " +
                    "where u.userName=:userName and u.password=:password and u.department=:department");
            query.setParameter("userName", userName);
            query.setParameter("password", password);
            query.setParameter("department", department);
            Long result = (Long)query.getSingleResult();
            session.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long isUserNameExist(String userName) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Long>query = session.createQuery("Select Count(u) from User u where u.userName=:userName");
            query.setParameter("userName", userName);
            Long result =  query.getSingleResult();
            session.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
