package com.tdtu.customer.controller;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Category;
import com.tdtu.library.model.Product;
import com.tdtu.library.service.CategoryService;
import com.tdtu.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("title", "Introduction page");
        return "home";
    }

    @GetMapping("/products")
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        List<ProductDto> productDtoList = productService.findAll();
        model.addAttribute("title", "Products");
        model.addAttribute("categories", categories);
        model.addAttribute("products", productDtoList);
        return "products";
    }


}
