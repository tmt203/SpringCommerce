package com.tdtu.customer.controller;

import com.tdtu.library.model.City;
import com.tdtu.library.model.Customer;
import com.tdtu.library.service.CityService;
import com.tdtu.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CityService cityService;


    @GetMapping("/account")
    public String accountHome(Model model , Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<City> cityList = cityService.getAll();
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        model.addAttribute("customer", customer);
        model.addAttribute("cities", cityList);



        return "account";
    }

    @RequestMapping(value = "/update-info", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(
            @ModelAttribute("customer") Customer customer,
            RedirectAttributes redirectAttributes,
            Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Customer updatedCustomer = customerService.updateInfo(customer);

        redirectAttributes.addFlashAttribute("customer", updatedCustomer);

        return "redirect:/account";
    }
}
