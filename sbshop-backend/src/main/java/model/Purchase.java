package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import helper.LocalDateTimeSerializer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PURCHASE")
public class Purchase extends PanacheEntity {

    @JsonBackReference("defaultClient")
    @ManyToOne
    private Client client;

    @JsonBackReference("defaultAddress")
    @ManyToOne
    private Address address;

    @JsonManagedReference("defaultPurchase")
    @OneToMany(mappedBy = "purchase",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval= true)
    private List<Item> items;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timeOfPurchase;
    private String deliveryType;
    private String paymentMethod;
    private Boolean isGift;
    private Boolean isConfirmed;
    private double price;
    private long clientId;
    private long addressId;

    // Default constructor for hibernate
    public Purchase() {
    }

    // Full constructor
    public Purchase(Long id, Long clientId, String deliveryType, String paymentMethod, Boolean isGift, Boolean isConfirmed) {
        this.id = id;
        this.clientId = clientId;
        this.deliveryType = deliveryType;
        this.paymentMethod = paymentMethod;
        this.isGift = isGift;
        this.isConfirmed = isConfirmed;
        this.price = 0.0;
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

    public List<Item> getItems() {
        return items;
    }
    public List<Item> getArticleItems(Article article) {
        return this.items.stream().filter(item -> item.getArticle() == article).collect(Collectors.toList());
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price ){
        this.price = price;
    }

    public void setPrice(List<Item> items) {
        double p = 0.0;
        for (Item item:items){
            p += item.getPrice();
        }
        p = Math.round(p*100)/100.0;
        this.price = p;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }
    public void setTimeOfPurchase() {
        this.timeOfPurchase = LocalDateTime.now();
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

    public Boolean getIsGift() {
        return isGift;
    }
    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }
    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
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
