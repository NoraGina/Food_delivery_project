package repository;

import model.OrderItem;

import java.util.Set;

public interface OrderItemDao {
    void createOrderItem(OrderItem orderItemInfo);
    Set<OrderItem> readAllOrderItem();
    OrderItem findOrderItemById(Long id);
    void deleteOrderItem(Long id);
    void updateOrderItem(OrderItem orderItemInfo);
}
