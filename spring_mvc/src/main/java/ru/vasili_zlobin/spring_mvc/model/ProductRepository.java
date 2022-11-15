package ru.vasili_zlobin.spring_mvc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static final int START_SIZE = 5;
    private static ProductRepository instance;
    private final List<Product> listProducts = new ArrayList<>();

    private ProductRepository() {
        for (int i = 0; i < START_SIZE; i++) {
            double cost = new BigDecimal(Double.toString(Math.random() * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            addProduct("Товар №" + (i + 1), cost);
        }
    }

    public void addProduct(String title, double cost) {
        int id = listProducts.isEmpty() ? 0 : listProducts.size();
        listProducts.add(new Product(id, title, cost));
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }
}