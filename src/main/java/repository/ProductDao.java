package repository;

import model.Product;

import java.util.Set;

public interface ProductDao {
    void createProduct(Product product);
    Set<Product> readAllProducts();
    Product findProductById(Long id);
    void deleteProduct(Long id);
    void updateProduct(Product product);
}
