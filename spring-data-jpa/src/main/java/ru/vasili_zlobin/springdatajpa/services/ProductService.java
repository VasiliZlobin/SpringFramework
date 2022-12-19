package ru.vasili_zlobin.springdatajpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasili_zlobin.springdatajpa.exceptions.ResourceNotFoundException;
import ru.vasili_zlobin.springdatajpa.model.Product;
import ru.vasili_zlobin.springdatajpa.specifications.ProductSpecification;

@Service
public class ProductService {
    private static final long MIN = 100L;
    private static final int PAGE_SIZE = 10;

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
    public Page<Product> find(Double min, Double max, Integer page) {
        Specification<Product> specification = Specification.where(null);
        if (min != null && min > 0) {
            specification.and(ProductSpecification.priceGreaterThan(min));
        }
        if (max != null && max > 0) {
            specification.and(ProductSpecification.priceLessThan(max));
        }
        return repository.findAll(PageRequest.of(page - 1, PAGE_SIZE));
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Товар с id %d не найден", id)));
    }

    @Transactional
    public void updateProduct(Product product) {
        repository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
