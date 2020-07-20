package model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table( name = "order_customer")
public class OrderCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_customer")
    private Long idOrderCustomer;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "orderCustomer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemSet;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", length = 4)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_type", length = 9)
    private Status status;

    public OrderCustomer() {
    }

    public Long getIdOrderCustomer() {
        return idOrderCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderCustomer)) return false;
        OrderCustomer that = (OrderCustomer) o;
        return idOrderCustomer.equals(that.idOrderCustomer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderCustomer);
    }

    public void setIdOrderCustomer(Long idOrderCustomer) {
        this.idOrderCustomer = idOrderCustomer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<OrderItem> getOrderItemSet() {
        return orderItemSet;
    }

    public void setOrderItemSet(Set<OrderItem> orderItemSet) {
        this.orderItemSet = orderItemSet;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderCustomer{" +
                "idOrderCustomer=" + idOrderCustomer +
                ", date=" + date +
                ", time=" + time +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                ", orderItemSet=" + orderItemSet +
                ", payment=" + payment +
                ", status='" + status + '\'' +
                '}';
    }

    public void displayInfoOrderCustomer(){
        System.out.println("Id order:"+ idOrderCustomer);
        System.out.println("Date: "+date);
        System.out.println("Time:"+time);
        System.out.println("Customer: "+customer.getFirstName()+" "+customer.getLastName());
        System.out.println("Restaurant: "+ restaurant.getRestaurantName());
        System.out.println("Payment: "+ payment);
        System.out.println("Status: "+ status);

    }
}
