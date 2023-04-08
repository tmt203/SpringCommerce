package com.tdtu.customer.controller;

import com.tdtu.library.dto.CustomerDto;
import com.tdtu.library.model.Admin;
import com.tdtu.library.model.Customer;
import com.tdtu.library.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                               BindingResult result,
                               Model model,
                               RedirectAttributes ra) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("title", "Login");
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("errorMessage", "Some error have been when sign up!");
                return "register";
            }
            String username = customerDto.getUsername();
            Customer customer = customerService.findByUsername(username);
            if (customer != null) {
                model.addAttribute("title", "Register");
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("message", "Your email has been registered!");
                model.addAttribute("messageType", "danger");
                return "register";
            }
            if (!customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
                model.addAttribute("title", "Register");
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("message", "Confirm password is not match!");
                model.addAttribute("messageType", "danger");
                return "register";
            }
            customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
            customerService.save(customerDto);
            model.addAttribute("title", "Register");
            model.addAttribute("customerDto", customerDto);
            model.addAttribute("message", "Register successfully :)");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("title", "Register");
            model.addAttribute("message", "Can not register. Server error!");
            model.addAttribute("messageType", "danger");
        }
        return "register";
    }

}
