package ru.vasili_zlobin.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasili_zlobin.spring.model.Cart;
import ru.vasili_zlobin.spring.model.ProductRepository;

@Configuration
public class JavaConfig {
    @Bean
    public ProductRepository repository() {
        return new ProductRepository();
    }

    @Bean
    public Cart cart() {
        return new Cart();
    }
}
