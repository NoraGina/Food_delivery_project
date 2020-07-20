package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="customer")
public class Customer extends Person{

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

   /* @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<OrderCustomer> orderCustomerSet = new HashSet<>();*/

    public Customer() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return password.equals(customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "password='" + password + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }

    public void displayInfoCustomer(){
        System.out.println("Id customer: "+getId());
        System.out.println("Customer name:"+ getFirstName()+ " "+ getLastName());
        System.out.println("Password: "+ password);
        System.out.println("Address: "+address);
        System.out.println("Phone: "+getPhone());
        System.out.println("Email: "+getEmail());
    }
}


