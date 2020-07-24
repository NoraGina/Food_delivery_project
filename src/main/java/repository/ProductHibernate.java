package repository;

import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductHibernate implements ProductDao {

    @Override
    public void createProduct(Product product) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(product);
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
    public Set<Product> readAllProducts() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Set<Product>productSet = new HashSet<>(session.createQuery("from Product", Product.class).list());
            return productSet;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product findProductById(Long id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Product product = session.find(Product.class, id);
            session.close();
            return product;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product findProductByNameAndRestaurant(String productName, Long idRestaurant) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(" from Product where productName=:productName and restaurant.idRestaurant=:idRestaurant");
            query.setParameter("productName", productName);
            query.setParameter("idRestaurant", idRestaurant);
            Product product = (Product) query.uniqueResult();
            session.close();
            return product;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> findAllProductsByIdRestaurant(Long restaurantId) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select productName from Product where restaurant_id=:restaurantId");
            query.setParameter("restaurantId", restaurantId);
            List<String> productsName = query.getResultList();
            session.close();
            return productsName;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        Transaction transaction = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(findProductById(id));
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
    public void updateProduct(Product product) {
        Transaction transaction = null;
        try{
            Session session= HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(product);
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
