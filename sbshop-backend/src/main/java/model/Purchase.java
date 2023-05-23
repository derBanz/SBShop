package model;

import jakarta.persistence.*;
import java.time.LocalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "PURCHASE")
public class Purchase extends PanacheEntity {

    @ManyToOne
    private Client client;

    @ManyToOne
    private Address address;

    private LocalDate timeOfPurchase;
    private String deliveryType;
    private String paymentMethod;
    private Boolean isGift;
    private Boolean isCompleted;
    private long clientId;
    private long addressId;

    // Default constructor for hibernate
    public Purchase() {
    }

    // Full constructor
    public Purchase(Long id, Client client, Address address, LocalDate timeOfPurchase, String deliveryType, String paymentMethod, Boolean isGift, Boolean isCompleted) {
        this.id = id;
        this.client = client;
        this.address = address;
        this.timeOfPurchase = timeOfPurchase;
        this.deliveryType = deliveryType;
        this.paymentMethod = paymentMethod;
        this.isGift = isGift;
        this.isCompleted = isCompleted;
        this.clientId = client.getId();
        this.addressId = address.getId();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getTimeOfPurchase() {
        return timeOfPurchase;
    }
    public void setTimeOfPurchase(LocalDate timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public String getDeliveryType() {
        return deliveryType;
    }
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getGift() {
        return isGift;
    }
    public void setGift(Boolean gift) {
        isGift = gift;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }
    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public long getClientId() {
        return clientId;
    }
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getAddressId() {
        return addressId;
    }
    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}
