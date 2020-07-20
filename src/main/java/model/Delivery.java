package model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delivery")
    private Long idDelivery;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", length = 10)
    private Vehicle vehicle;

    @Column(name = "license_plate")
    private String licensePlate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.EAGER)
    private OrderProvider orderProvider;

    public Delivery() {
    }

    public Long getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderProvider getOrderProvider() {
        return orderProvider;
    }

    public void setOrderProvider(OrderProvider orderProvider) {
        this.orderProvider = orderProvider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery)) return false;
        Delivery delivery = (Delivery) o;
        return idDelivery.equals(delivery.idDelivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDelivery);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "idDelivery=" + idDelivery +
                ", vehicle=" + vehicle +
                ", licensePlate='" + licensePlate + '\'' +
                ", user=" + user +
                ", orderProvider=" + orderProvider +
                '}';
    }

    public void displayInfoDelivery(){
        System.out.println("Delivery id:"+idDelivery);
        System.out.println("Vehicle: "+vehicle);
        System.out.println("License plate: "+licensePlate);
        System.out.println("User: "+user.getFirstName()+" "+user.getLastName());
    }
}
