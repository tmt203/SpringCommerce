package com.tdtu.library.service;

import com.tdtu.library.model.Cart;
import com.tdtu.library.model.Customer;
import com.tdtu.library.model.Item;
import com.tdtu.library.model.Product;

public interface CartService {
    Cart addItem2Cart(Product product, int quantity, Customer customer);
    Cart updateItemInCart(Product product, int quantity, Customer customer);
    Cart deleteItemFromCart(Product product, Customer customer);
}
