package com.tdtu.admin.controller;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Category;
import com.tdtu.library.service.CategoryService;
import com.tdtu.library.service.ProductService;
import com.tdtu.library.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

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
    public String addProductForm(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("title","Add product");
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categories", categories);
        return "add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("productDto") ProductDto productDto,
                              @RequestParam("productImage")MultipartFile productImage,
                              RedirectAttributes ra) {
        try {
            productService.save(productImage, productDto);
            ra.addFlashAttribute("successMessage", "Add product successfully");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Add product failed !");
        }
        return "redirect:/products";
    }

    @GetMapping("/update-product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        return "update-product";
    }
}
