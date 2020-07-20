package repository;

import model.OrderCustomer;

import java.util.Set;

public interface OrderCustomerDao {

    void createOrderCustomer(OrderCustomer orderCustomer);
    Set<OrderCustomer> readAllOrderCustomer();
    OrderCustomer findOrderCustomerById(Long id);
    void deleteCustomerOrder(Long id);
    void updateCustomerOrder(OrderCustomer orderCustomer);
    Long retrieveIdCustomerOrder();
}
