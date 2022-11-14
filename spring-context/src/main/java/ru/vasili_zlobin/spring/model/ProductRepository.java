package ru.vasili_zlobin.spring.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    public static final int SIZE = 5;
    private final List<Product> listProducts = new ArrayList<>();

    public ProductRepository() {
        for (int i = 0; i < SIZE; i++) {
            double cost = new BigDecimal(Double.toString(Math.random() * 100)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            listProducts.add(new Product(i, "Товар №" + (i + 1), cost));
        }
    }

    public Product getProductForId(int id) {
        if (id < listProducts.size()) {
            return listProducts.get(id);
        }
        return null;
    }
}
