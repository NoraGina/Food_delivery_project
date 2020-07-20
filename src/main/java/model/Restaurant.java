package model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "food_delivery", name="restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurant")
    private Long idRestaurant;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "restaurant_email", nullable = false)
    private String restaurantEmail;

    @Column(name = "restaurant_phone", nullable = false)
    private String restaurantPhone;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product>productSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
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
