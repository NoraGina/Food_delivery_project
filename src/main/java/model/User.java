package model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="user")
public class User extends Person{

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "department_type", length = 13)
    private Department department;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Product> productSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Restaurant>restaurantSet;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public Set<Restaurant> getRestaurantSet() {
        return restaurantSet;
    }

    public void setRestaurantSet(Set<Restaurant> restaurantSet) {
        this.restaurantSet = restaurantSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return userName.equals(user.userName) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + password + '\'' +
                ", department=" + department +
                ", productSet=" + productSet +
                ", restaurantSet=" + restaurantSet +
                "} " + super.toString();
    }

    public void displayInfoUser(){
        System.out.println("Id:" + getId());
        System.out.println("First name:" + getFirstName());
        System.out.println("Last name: "+ getLastName());
        System.out.println("User name: "+userName);
        System.out.println("Password:" + password);
        System.out.println("Department:" + department);
        System.out.println("Phone: " + getPhone());
        System.out.println("Email :"+ getEmail());
    }
}
