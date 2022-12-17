package ru.vasili_zlobin.springdatajpa.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.vasili_zlobin.springdatajpa.model.Product;
import ru.vasili_zlobin.springdatajpa.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public Page<Product> getListProducts(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(required = false) Double min,
                                         @RequestParam(required = false) Double max) {
        if (page < 1) {
            page = 1;
        }
        return service.find(min, max, page);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        service.addProduct(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
