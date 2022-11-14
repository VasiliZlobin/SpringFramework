package ru.vasili_zlobin.spring_mvc.model;

public class Product {
    private final int id;
    private final String title;
    private final double cost;

    @Override
    public String toString() {
        return String.format("(%s) %s; Стоимость: %s", id, title, cost);
    }

    public Product(int id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }
}
