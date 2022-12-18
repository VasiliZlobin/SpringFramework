package ru.vasili_zlobin.springdatajpa.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cart {
    private final Map<Long, Integer> goods = new HashMap<>();

    public Cart() {
    }

    public void add(Long id) {
        int quantity = 0;
        if (goods.containsKey(id)) {
            quantity = goods.get(id);
        }
        goods.put(id, quantity + 1);
    }

    public void remove(Long id) {
        if (!goods.containsKey(id)) {
            return;
        }
        int quantity = goods.get(id);
        if (quantity < 2) {
            goods.remove(id);
        } else {
            goods.put(id, quantity - 1);
        }
    }

    public void deleteAll(Long id) {
        if (goods.containsKey(id)) {
            goods.remove(id);
        }
    }

    public Integer getQuantity(Long id) {
        Integer result = 0;
        if (goods.containsKey(id)) {
            result = goods.get(id);
        }
        return result;
    }
}
