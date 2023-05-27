package model;

import jakarta.persistence.*;
import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ADDRESS")
public class Address extends PanacheEntity {

    @JsonBackReference
    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;
    private Long clientId;


    // Default constructor for hibernate
    public Address() {
    }

    // Full constructor
    public Address(Client client, String street, String houseNumber, String zipCode, String city, String country) {
        this.client = client;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
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

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Long getClientId() {
        return clientId;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
