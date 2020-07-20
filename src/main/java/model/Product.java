package model;

import java.util.Objects;

public class Product {

    private Long idProduct;
    private String productName;
    private String description;
    private Double price;
    private Restaurant restaurant;
    private User user;

    public Product() {
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return idProduct.equals(product.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                ", user=" + user +
                '}';
    }

    public void displayInfoProduct(){
        System.out.println("Id:" + idProduct);
        System.out.println("Name:" + productName);
        System.out.println("Ingredients:" + description);
        System.out.println("Price:" + price);
        System.out.println("Restaurant: " + restaurant.getRestaurantName());
        System.out.println("User :"+ user.getFirstName()+" "+user.getLastName());
    }
}
