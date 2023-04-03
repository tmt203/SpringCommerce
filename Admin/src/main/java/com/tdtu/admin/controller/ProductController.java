package com.tdtu.admin.controller;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.service.ProductService;
import com.tdtu.library.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model, Principal principal) {
        // Principal: Check user already logged in or not
        if (principal == null) {
            return "redirect:/login";
        }
        List<ProductDto> productDtoList = productService.findAll();
        model.addAttribute("title", "Product Management");
        model.addAttribute("products", productDtoList);
        model.addAttribute("size", productDtoList.size());
        return "products";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "add-product";
    }
}
