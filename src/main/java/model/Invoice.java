package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice", nullable = false)
    private Long idInvoice;

    @OneToOne(mappedBy = "invoice", fetch = FetchType.EAGER)
    private OrderProvider orderProvider;

    @Column(name = "value", nullable = false)
    private Double value;

    public Invoice() {
    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public OrderProvider getOrderProvider() {
        return orderProvider;
    }

    public void setOrderProvider(OrderProvider orderProvider) {
        this.orderProvider = orderProvider;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return idInvoice.equals(invoice.idInvoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvoice);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "idInvoice=" + idInvoice +
                ", orderProvider=" + orderProvider +
                ", value=" + value +
                '}';
    }

    public void displayInfoInvoice(){
        System.out.println("Invoice id:"+idInvoice);
        System.out.println("Order id:"+ orderProvider.getIdOrderProvider());
        System.out.println("Value: "+value);
    }
}
