package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "CLIENT")
public class Client extends PanacheEntity {

    @JsonManagedReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @JsonManagedReference("defaultClient")
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    private String name;
    private String surname;
    private String title;
    private Date dateOfBirth;

    // Default constructor for hibernate
    public Client() {
    }

    // Full constructor
    public Client(String surname, String name, String title, Date dateOfBirth) {
        this.surname = surname;
        this.name = name;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
