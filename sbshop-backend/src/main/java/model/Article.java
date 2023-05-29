package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "ARTICLE")
public class Article extends PanacheEntity {

    @JsonManagedReference("defaultArticle")
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    private String name;
    private double height;
    private double width;
    private double length;
    private String description;
    private double price;

    // Default constructor for hibernate
    public Article() {
    }

    // Full constructor
    public Article(String name, double height, double width, double length, String description, double price) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;
        this.description = description;
        this.price = price;
    }

    // Getters and setters
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return this.height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return this.length;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
