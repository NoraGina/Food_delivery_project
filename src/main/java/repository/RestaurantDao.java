package repository;

import model.Restaurant;

import java.util.Set;

public interface RestaurantDao {
    void createRestaurant(Restaurant restaurant);
    Set<Restaurant> readAllRestaurants();
    Restaurant findRestaurantById(Long id);
    void deleteRestaurant(Long id);
    void updateRestaurant(Restaurant restaurant);
}
