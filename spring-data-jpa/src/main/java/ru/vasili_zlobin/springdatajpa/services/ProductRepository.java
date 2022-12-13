package ru.vasili_zlobin.springdatajpa.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vasili_zlobin.springdatajpa.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "FROM Product p WHERE p.price > :min")
    List<Product> getWithPriceMin(Double min);

    @Query(value = "FROM Product p WHERE p.price < :max")
    List<Product> getWithPriceMax(Double max);

    @Query(value = "FROM Product p WHERE p.price > :min AND p.price < :max")
    List<Product> getWithPriceMinAnMax(Double min, Double max);
}
