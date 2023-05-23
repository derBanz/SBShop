package model;

import jakarta.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "ITEM")
public class Item extends PanacheEntity {

    @ManyToOne
    private Purchase purchase;

    @ManyToOne
    private Article article;

    private double price;
    private long purchaseId;
    private long articleId;

    // Default constructor for hibernate
    public Item() {
    }

    // Full constructor
    public Item(Long id, Purchase purchase, Article article, double price) {
        this.id = id;
        this.purchase = purchase;
        this.article = article;
        this.price = price;
        this.purchaseId = purchase.getId();
        this.articleId = article.getId();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Article getArticle() {
        return article;
    }
    public void setArticle(Article article) {
        this.article = article;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public long getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public long getArticleId() {
        return articleId;
    }
    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
