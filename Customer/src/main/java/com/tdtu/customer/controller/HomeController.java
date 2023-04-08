package com.tdtu.customer.controller;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Cart;
import com.tdtu.library.model.Category;
import com.tdtu.library.model.Customer;
import com.tdtu.library.model.Product;
import com.tdtu.library.service.CategoryService;
import com.tdtu.library.service.CustomerService;
import com.tdtu.library.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
    public String homePage(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            Cart cart = customer.getCart();
            session.setAttribute("totalItems", cart.getTotalItems());
        } else {
            System.out.println("Enter here!!");
            session.setAttribute("username", null);
        }
        System.out.println("Session:" + session.getAttribute("username"));
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
