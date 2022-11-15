package ru.vasili_zlobin.spring_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.vasili_zlobin.spring_mvc.model.Product;
import ru.vasili_zlobin.spring_mvc.model.ProductRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/products")
public class RepositoryController {

    @GetMapping
    public String getViewListProduct(Model model, HttpServletResponse response) {
        model.addAttribute(getListProduct());
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getProductInfo(@PathVariable Integer id) {
        String result = "Неправильный ID продукта";
        if (id != null && id < getListProduct().size()) {
            result = getListProduct().get(id).toString();
        }
        return String.format("<h1>%s</h1>", result);
    }

    private List<Product> getListProduct() {
        return ProductRepository.getInstance().getListProducts();
    }
}
