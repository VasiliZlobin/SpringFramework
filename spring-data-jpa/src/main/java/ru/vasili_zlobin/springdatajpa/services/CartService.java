package ru.vasili_zlobin.springdatajpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vasili_zlobin.springdatajpa.model.Cart;

@Service
public class CartService {
    @Autowired
    private Cart cart;

    public void change(Long id, Integer delta) {
        if (delta == 1) {
            cart.add(id);
        } else {
            cart.remove(id);
        }
    }

    public void delete(Long id) {
        cart.deleteAll(id);
    }

    public Integer getQuantity(Long id) {
        return cart.getQuantity(id);
    }
}
