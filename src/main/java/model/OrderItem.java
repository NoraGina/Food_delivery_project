package model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "food_delivery", name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_item")
    private Long idOrderItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order_customer")
    @NotFound(action = NotFoundAction.IGNORE)
    private OrderCustomer orderCustomer;

    public OrderItem() {
    }

    public Long getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(Long idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderCustomer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(OrderCustomer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return idOrderItem.equals(orderItem.idOrderItem) &&
                product.equals(orderItem.product) &&
                quantity.equals(orderItem.quantity) &&
                orderCustomer.equals(orderItem.orderCustomer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderItem, product, quantity, orderCustomer);
    }
    @Override
    public String toString() {
        return "OrderItem{" +
                "idOrderItem=" + idOrderItem +
                ", product=" + product +
                ", quantity=" + quantity +
                ", orderCustomer=" + orderCustomer +
                '}';
    }

    public void displayInfoOrderItem(){
        System.out.println("Id item: "+ idOrderItem);
        System.out.println("Product: "+product.getProductName());
        System.out.println("Price: "+product.getPrice());
        System.out.println("Quantity: "+quantity);
        System.out.println("Value: "+ makeValue());
        //System.out.println("Id order:"+orderCustomer.getIdOrderCustomer());
    }

    public Double makeValue(){
        return  product.getPrice()*quantity;
    }
}
