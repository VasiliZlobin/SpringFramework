package ru.vasili_zlobin.springdatajpa.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.vasili_zlobin.springdatajpa.dto.ProductDto;
import ru.vasili_zlobin.springdatajpa.model.Product;
import ru.vasili_zlobin.springdatajpa.services.ProductService;

@RestController
@RequestMapping("/api_1.0/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public Page<ProductDto> getListProducts(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(required = false) Double min,
                                         @RequestParam(required = false) Double max) {
        if (page < 1) {
            page = 1;
        }
        return service.find(min, max, page).map(product -> new ProductDto(product));
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return new ProductDto(service.getProductById(id));
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto dto) {
        service.updateProduct(new Product(dto.getTitle(), dto.getPrice()));
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto dto) {
        Product product = new Product(dto.getTitle(), dto.getPrice());
        product.setId(dto.getId());
        service.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
