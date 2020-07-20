package repository;

import model.Customer;

import java.util.Set;

public interface CustomerDao {

    void createCustomer(Customer customer);
    Set<Customer> readAllCustomer();
    Customer findCustomerById(Long id);
    void deleteCustomer(Long id);
    void updateCustomer(Customer customer);
}
