package repository;

import model.OrderItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class OrderItemHibernate implements OrderItemDao {

    @Override
    public void createOrderItem(OrderItem orderItem) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(orderItem);
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
    public Set<OrderItem> readAllOrderItem() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Set<OrderItem> orderItemSet = new HashSet<>(session.createQuery("from OrderItem", OrderItem.class).list());
            session.close();
            return orderItemSet;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderItem findOrderItemById(Long id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            OrderItem orderItem = session.find(OrderItem.class, id);
            session.close();
            return orderItem;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteOrderItem(Long id) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(findOrderItemById(id));
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
    public void updateOrderItem(OrderItem orderItem) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(orderItem);
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
