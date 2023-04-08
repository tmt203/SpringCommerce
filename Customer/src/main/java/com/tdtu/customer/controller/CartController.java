package com.tdtu.customer.controller;

import com.tdtu.library.model.Cart;
import com.tdtu.library.model.Customer;
import com.tdtu.library.model.Product;
import com.tdtu.library.service.CartService;
import com.tdtu.library.service.CustomerService;
import com.tdtu.library.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession session) {
        // Make sure user logged in to add item to cart
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        Cart cart = customer.getCart();

        if (cart == null) {
            model.addAttribute("checkCart", "No items in your cart");
        }

        session.setAttribute("totalItems", cart.getTotalItems());
        model.addAttribute("subTotal", cart.getTotalPrices());
        model.addAttribute("title", "Cart");
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") Long productId,
                            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                            Principal principal,
                            Model model,
                            HttpServletRequest req) {
        if (principal == null) {
            return "redirect:/login";
        }
        Product product = productService.getProductById(productId);
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        Cart cart = cartService.addItem2Cart(product, quantity, customer);
        return "redirect:" + req.getHeader("Referer");
    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    public String updateItemInCart(@RequestParam("quantity") int quantity,
                                   @RequestParam("id") Long productId,
                                   Model model,
                                   Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            Cart cart = cartService.updateItemInCart(product, quantity, customer);

            model.addAttribute("cart", cart);
            return "redirect:/cart";
        }
    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
    public String deleteItemFromCart(@RequestParam("id") Long productId,
                                     Model model,
                                     Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(customer.getId());
            Cart cart = cartService.deleteItemFromCart(product, customer);

            model.addAttribute("cart", cart);
            return "redirect:/cart";
        }
    }

}
