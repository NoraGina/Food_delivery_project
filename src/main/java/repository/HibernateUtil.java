package repository;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/food_delivery?serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root0000");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");//"create-drop" "none"
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Restaurant.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(OrderCustomer.class);
                configuration.addAnnotatedClass(OrderItem.class);
                configuration.addAnnotatedClass(User.class);
                //configuration.addAnnotatedClass(DeliveryPerson.class);
                //configuration.addAnnotatedClass(Delivery.class);
                //configuration.addAnnotatedClass(Invoice.class);
                //configuration.addAnnotatedClass(OrderProvider.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;

    }
}
