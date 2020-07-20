package model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table( name = "delivery_person")
public class DeliveryPerson extends Person{

    @Column(name = "id_card", nullable = false)
    private String idCard;


    @OneToMany(mappedBy = "deliveryPerson", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderProvider> orderProviderSet;

    public DeliveryPerson() {
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Set<OrderProvider> getOrderProviderSet() {
        return orderProviderSet;
    }

    public void setOrderProviderSet(Set<OrderProvider> orderProviderSet) {
        this.orderProviderSet = orderProviderSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryPerson)) return false;
        DeliveryPerson that = (DeliveryPerson) o;
        return idCard.equals(that.idCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCard);
    }

    @Override
    public String toString() {
        return "DeliveryPerson{" +
                "idCard='" + idCard + '\'' +
                ", orderProviderSet=" + orderProviderSet +
                '}';
    }

    public void displayInfoDeliveryPerson(){
        System.out.println("Delivery person id:"+getId());
        System.out.println("Delivery person name: "+getFirstName()+ " "+getLastName());
        System.out.println("Delivery person phone: "+getPhone());
        System.out.println("Delivery person email: "+getEmail());
        System.out.println("Delivery person id card: "+ idCard);
    }
}
