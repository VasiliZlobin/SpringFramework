package ru.vasili_zlobin.hibernate.model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import ru.vasili_zlobin.hibernate.controllers.ConnectFactory;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    public Product() {
    }

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    @Override
    public String toString() {
        return String.format("(%s) %s, Стоимость: %s", id, title, price);
    }
}
