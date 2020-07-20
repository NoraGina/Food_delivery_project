package model;

import java.util.Objects;
import java.util.Set;

public class Restaurant {

    private Long idRestaurant;
    private String restaurantName;
    private String restaurantEmail;
    private String restaurantPhone;
    private Set<Product>productSet;
    private User user;

    public Restaurant() {
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        Restaurant that = (Restaurant) o;
        return idRestaurant.equals(that.idRestaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRestaurant);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "idRestaurant=" + idRestaurant +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantEmail='" + restaurantEmail + '\'' +
                ", restaurantPhone='" + restaurantPhone + '\'' +
                ", productSet=" + productSet +
                ", user=" + user +
                '}';
    }

    public void displayInfoRestaurant() {
        System.out.println("Id:" + idRestaurant);
        System.out.println("Name:" + restaurantName);
        System.out.println("Phone:" + restaurantPhone);
        System.out.println("Email: " + restaurantEmail);
    }
}
