package ru.vasili_zlobin.springdatajpa.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.vasili_zlobin.springdatajpa.dto.ProductDto;
import ru.vasili_zlobin.springdatajpa.model.Product;
import ru.vasili_zlobin.springdatajpa.services.CartService;
import ru.vasili_zlobin.springdatajpa.services.ProductService;
import ru.vasili_zlobin.springdatajpa.validators.ProductValidator;

@RestController
@RequestMapping("/api_1.0/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private ProductValidator validator;

    @Autowired
    private CartService cartService;

    @GetMapping
    public Page<ProductDto> getListProducts(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(required = false) Double min,
                                         @RequestParam(required = false) Double max) {
        if (page < 1) {
            page = 1;
        }
        return service.find(min, max, page).map(product -> new ProductDto(product, cartService.getQuantity(product.getId())));
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return new ProductDto(service.getProductById(id));
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto dto) {
        validator.validate(dto);
        service.updateProduct(new Product(dto.getTitle(), dto.getPrice()));
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto dto) {
        validator.validate(dto);
        Product product = service.getProductById(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        service.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cartService.delete(id);
        service.deleteById(id);
    }

    @GetMapping("/change_cart")
    public void changeCart(@RequestParam Long id, @RequestParam Integer delta) {
        cartService.change(id, delta);
    }
}
