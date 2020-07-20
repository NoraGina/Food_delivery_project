package repository;

import model.Restaurant;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class RestaurantHibernate implements RestaurantDao {
    @Override
    public void createRestaurant(Restaurant restaurant) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(restaurant);
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
    public Set<Restaurant> readAllRestaurants() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            return new HashSet<>(session.createQuery("from Restaurant", Restaurant.class).list());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Restaurant findRestaurantById(Long id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Restaurant restaurant = session.find(Restaurant.class, id );
            session.close();
            return restaurant;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteRestaurant(Long id) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(findRestaurantById(id));
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(restaurant);
            transaction.commit();
            session.close();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
