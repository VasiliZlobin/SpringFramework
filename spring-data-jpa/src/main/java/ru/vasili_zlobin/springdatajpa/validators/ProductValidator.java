package ru.vasili_zlobin.springdatajpa.validators;

import org.springframework.stereotype.Component;
import ru.vasili_zlobin.springdatajpa.dto.ProductDto;
import ru.vasili_zlobin.springdatajpa.exceptions.ValidateException;

import java.util.LinkedList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto dto) {
        List<String> errors = new LinkedList<>();
        if (dto.getPrice() <= 0) {
            errors.add("Цена должна быть больше 0!");
        }
        if (dto.getTitle().isBlank()) {
            errors.add("Требуется указать наименование товара!");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException(errors);
        }
    }
}
