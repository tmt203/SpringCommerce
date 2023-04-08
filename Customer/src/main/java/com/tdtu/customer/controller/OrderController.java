package com.tdtu.customer.controller;

import com.tdtu.library.model.Cart;
import com.tdtu.library.model.Customer;
import com.tdtu.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class OrderController {

    @Autowired
    private CustomerService customerService;


//    @GetMapping("/check-out")
//    public String checkout(Model model, Principal principal){
//        if(principal == null){
//            return "redirect:/login";
//        }
//        String username = principal.getName();
//        Customer customer = customerService.findByUsername(username);
//        if(customer.getPhoneNumber().trim().isEmpty() || customer.getAddress().trim().isEmpty()
//                || customer.getCity().trim().isEmpty() || customer.getCountry().trim().isEmpty()){
//
//            model.addAttribute("customer", customer);
//            model.addAttribute("error", "You must fill the information after checkout!");
//            return "account";
//        }else{
//            model.addAttribute("customer", customer);
//            Cart cart = customer.getCart();
//            model.addAttribute("cart", cart);
//        }
//        return "checkout";
//    }


    @GetMapping("/order")
    public String order(){
        return "order";
    }

}