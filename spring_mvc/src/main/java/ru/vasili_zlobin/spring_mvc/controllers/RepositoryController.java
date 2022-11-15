package ru.vasili_zlobin.spring_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vasili_zlobin.spring_mvc.model.Product;
import ru.vasili_zlobin.spring_mvc.model.ProductRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/products")
public class RepositoryController {

    @GetMapping
    public String getViewListProduct(Model model) {
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

    @GetMapping("/form_add")
    public String viewFormAdd() {
        return "product_add";
    }

    @GetMapping("/product_add")
    public String addProduct(@RequestParam(defaultValue = "") String title, @RequestParam(defaultValue = "-1") Double cost) {
        if (!title.isEmpty() && cost > 0) {
            ProductRepository.getInstance().addProduct(title, cost);
        }
        return "redirect:/products";
    }

    private List<Product> getListProduct() {
        return ProductRepository.getInstance().getListProducts();
    }
}
