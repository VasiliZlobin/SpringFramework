package ru.vasili_zlobin.hibernate;

import ru.vasili_zlobin.hibernate.controllers.ProductDao;
import ru.vasili_zlobin.hibernate.model.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        Product gen;
        for (long i = 1; i < 6; i++) {
            gen = new Product("", Math.random() * 1000);
            dao.saveOrUpdate(gen);
            gen.setTitle(String.format("Товар №%d", gen.getId()));
            dao.saveOrUpdate(gen);
        }
        List<Product> current = dao.findAll();
        System.out.println(current);
        dao.deleteById(current.get(3).getId());
        Product change = dao.findById(current.get(0).getId());
        if (change != null) {
            change.setPrice(change.getPrice() * 2);
            dao.saveOrUpdate(change);
        }
        System.out.println(dao.findAll());
    }
}