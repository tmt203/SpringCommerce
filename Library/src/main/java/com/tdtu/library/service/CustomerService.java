package com.tdtu.library.service;

import com.tdtu.library.dto.CustomerDto;
import com.tdtu.library.model.Customer;

public interface CustomerService {

    Customer findByUsername(String username);
    CustomerDto save(CustomerDto customerDto);
    Customer updateInfo(Customer customer);
}
