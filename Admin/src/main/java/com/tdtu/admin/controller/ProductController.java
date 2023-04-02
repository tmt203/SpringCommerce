package com.tdtu.admin.controller;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.service.ProductService;
import com.tdtu.library.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductDto> productDtoList = productService.findAll();
        model.addAttribute("title", "Product");
        model.addAttribute("products", productDtoList);
        model.addAttribute("size", productDtoList.size());
        return "products";
    }
}
