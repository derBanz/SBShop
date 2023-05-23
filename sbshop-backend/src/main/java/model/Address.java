package model;

import jakarta.persistence.*;
import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "ADDRESS")
public class Address extends PanacheEntity {

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    private String street;
    private String houseNumber;
    private String zipCode;
    private String country;
    private String type;
    private long clientId;


    // Default constructor for hibernate
    public Address() {
    }

    // Full constructor
    public Address(Long id, Client client, List<Purchase> purchases, String street, String houseNumber, String zipCode, String country, String type) {
        this.id = id;
        this.client = client;
        this.purchases = purchases;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.country = country;
        this.type = type;
        this.clientId = client.getId();
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

    public List<Purchase> getPurchases() {
        return purchases;
    }
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public long getClientId() {
        return clientId;
    }
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
