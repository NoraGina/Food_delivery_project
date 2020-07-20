package model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "order_provider")
public class OrderProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_provider", nullable = false)
    private Long idOrderProvider;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @OneToOne
    @JoinColumn(name = "order_customer_id")
    private OrderCustomer orderCustomer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_person")
    @NotFound(action = NotFoundAction.IGNORE)
    private DeliveryPerson deliveryPerson;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public OrderProvider() {
    }

    public Long getIdOrderProvider() {
        return idOrderProvider;
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

    public OrderCustomer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(OrderCustomer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProvider)) return false;
        OrderProvider that = (OrderProvider) o;
        return idOrderProvider.equals(that.idOrderProvider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderProvider);
    }

    @Override
    public String toString() {
        return "OrderProvider{" +
                "idOrderProvider=" + idOrderProvider +
                ", date=" + date +
                ", time=" + time +
                ", orderCustomer=" + orderCustomer +
                ", user=" + user +
                ", deliveryPerson=" + deliveryPerson +
                ", delivery=" + delivery +
                ", invoice=" + invoice +
                '}';
    }

    public void displayInfoOrderProvider(){
        System.out.println("Id order:"+idOrderProvider);
        System.out.println("Date: "+date);
        System.out.println("Time: "+time);
        System.out.println("Order customer: "+orderCustomer.getIdOrderCustomer());
        System.out.println("User: "+user.getFirstName()+" "+user.getLastName());
        System.out.println("Invoice: "+ invoice.getValue());
        System.out.println("Delivery: "+delivery.getIdDelivery());
        System.out.println("Delivery person: "+deliveryPerson.getFirstName()+" "+deliveryPerson.getLastName());

    }
}
