package com.tdtu.customer.controller;

import com.tdtu.library.model.Cart;
import com.tdtu.library.model.Customer;
import com.tdtu.library.model.Order;
import com.tdtu.library.service.CustomerService;
import com.tdtu.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/check-out")
    public String checkout(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        if(customer.getPhoneNumber().trim().isEmpty() || customer.getAddress().trim().isEmpty()
                || customer.getCity().trim().isEmpty()) {

            model.addAttribute("customer", customer);
            model.addAttribute("error", "You must fill the information before checkout!");
            return "account";
        }else{
            model.addAttribute("customer", customer);
            Cart cart = customer.getCart();
            model.addAttribute("cart", cart);
        }
        return "check-out";
    }


    @GetMapping("/order")
    public String order(Principal principal, Model model){
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        List<Order> orderList = customer.getOrders();

        model.addAttribute("orders", orderList);
        return "order";
    }

    @GetMapping("save-order")
    public String saveOrder(Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        Cart cart = customer.getCart();

        orderService.saveOrder(cart);
        return "redirect:/order";
    }

}