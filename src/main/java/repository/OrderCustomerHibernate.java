package repository;

import model.OrderCustomer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.HashSet;
import java.util.Set;

public class OrderCustomerHibernate implements OrderCustomerDao {

    @Override
    public void createOrderCustomer(OrderCustomer orderCustomer) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(orderCustomer);
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
    public Set<OrderCustomer> readAllOrderCustomer() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Set<OrderCustomer>orderCustomerSet = new HashSet<>(session.createQuery("from OrderCustomer", OrderCustomer.class).list());
            session.close();
            return orderCustomerSet;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderCustomer findOrderCustomerById(Long id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            OrderCustomer orderCustomer = session.find(OrderCustomer.class, id);
            session.close();
            return orderCustomer;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteCustomerOrder(Long id) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(findOrderCustomerById(id));
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
    public void updateCustomerOrder(OrderCustomer orderCustomer) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(orderCustomer);
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
    public Long retrieveIdCustomerOrder() {
        Long id;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            NativeQuery query = session.createSQLQuery("select max(id_order_customer) from order_customer");
            id = (Long) query.getSingleResult();
            return  id;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
