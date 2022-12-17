package ru.vasili_zlobin.springdatajpa.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasili_zlobin.springdatajpa.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
