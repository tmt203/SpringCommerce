package com.tdtu.library.service.impl;

import com.tdtu.library.dto.CustomerDto;
import com.tdtu.library.model.Customer;
import com.tdtu.library.repository.CustomerRepository;
import com.tdtu.library.repository.RoleRepository;
import com.tdtu.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setRoles(Arrays.asList(roleRepo.findByName("CUSTOMER")));

        Customer savedCustomer = customerRepo.save(customer);
        return convert2Dto(savedCustomer);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepo.findByUsername(username);
    }

    @Override
    public Customer updateInfo(Customer customer) {
        Customer updatedCustomer = customerRepo.findByUsername(customer.getUsername());
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setCity(customer.getCity());
        return customerRepo.save(updatedCustomer);
    }

    private CustomerDto convert2Dto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setUsername(customer.getUsername());
        return customerDto;
    }
}
