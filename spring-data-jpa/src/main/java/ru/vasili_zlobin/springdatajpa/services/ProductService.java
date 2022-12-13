package ru.vasili_zlobin.springdatajpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasili_zlobin.springdatajpa.model.Product;

import java.util.List;

@Service
public class ProductService {
    private static final long MIN = 20L;

    @Autowired
    private ProductRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        Long count = MIN - repository.count();
        for (int i = 1; i <= count; i++) {
            repository.save(new Product(String.format("Товар № %d", i), Math.random() * 100));
        }
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Product> getProducts(Double min, Double max) {
        if (min > 0 && max > 0) {
            return repository.getWithPriceMinAnMax(min, max);
        }
        if (min > 0) {
            return repository.getWithPriceMin(min);
        }
        if (max > 0) {
            return repository.getWithPriceMax(max);
        }
        return repository.findAll();
    }

    @Transactional
    public void addProduct(Product product) {
        repository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
