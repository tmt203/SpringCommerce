package com.tdtu.library.service;

import com.tdtu.library.model.Cart;

public interface OrderService {
    void saveOrder(Cart cart);
    void acceptOrder(Long id);
    void cancelOrder(Long id);
}
