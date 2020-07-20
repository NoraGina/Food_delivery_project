package repository;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class CustomerHibernate implements CustomerDao {

    @Override
    public void createCustomer(Customer customer) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(customer);
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
    public Set<Customer> readAllCustomer() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Set<Customer>customerInfos = new HashSet<>(session.createQuery("from Customer", Customer.class).list());
            session.close();
            return customerInfos;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer findCustomerById(Long id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Customer customer = session.find(Customer.class, id);
            session.close();
            return customer;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(findCustomerById(id));
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
    public void updateCustomer(Customer customer) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(customer);
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
