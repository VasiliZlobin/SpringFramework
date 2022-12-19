package ru.vasili_zlobin.springdatajpa.dto;

import ru.vasili_zlobin.springdatajpa.model.Product;

public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private Integer quantityInCart;

    public ProductDto() {
    }

    public ProductDto(Product product, Integer quantityInCart) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.quantityInCart = quantityInCart;
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.quantityInCart = 0;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(Integer quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    @Override
    public String toString() {
        return String.format("(%s) %s, Стоимость: %s", id, title, price);
    }
}
