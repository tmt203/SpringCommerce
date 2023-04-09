package com.tdtu.library.service;

import com.tdtu.library.dto.OrderDto;
import com.tdtu.library.model.Cart;
import com.tdtu.library.model.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Cart cart);
    void acceptOrder(Long id);
    void cancelOrder(Long id);

    // ---
    List<OrderDto> getAllOrders();
    List<OrderDto> findAllByCustomerId(Long id);
}
