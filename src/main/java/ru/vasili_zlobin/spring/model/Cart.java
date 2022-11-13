package ru.vasili_zlobin.spring.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Cart {
    private List<Product> products = new ArrayList<>();

    public void add(int id) {
        if (getPositionProduct(id) == -1) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.vasili_zlobin.spring.model");
            products.add(context.getBean(ProductRepository.class).getProductForId(id));
        }
    }

    public void remove(int id) {
        int position = getPositionProduct(id);
        if (position != -1) {
            products.remove(position);
        }
    }

    public void printInfo() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println("В корзине:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private int getPositionProduct(int id) {
        int position = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                position = i;
                break;
            }
        }
        return position;
    }
}
