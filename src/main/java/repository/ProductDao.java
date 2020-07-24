package repository;

import model.Product;

import java.util.List;
import java.util.Set;

public interface ProductDao {
    void createProduct(Product product);
    Set<Product> readAllProducts();
    Product findProductById(Long id);
    Product findProductByNameAndRestaurant(String productName, Long idRestaurant);
    List<String> findAllProductsByIdRestaurant(Long restaurantId);
    void deleteProduct(Long id);
    void updateProduct(Product product);

}
