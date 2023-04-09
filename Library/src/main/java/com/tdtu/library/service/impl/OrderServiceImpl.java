package com.tdtu.library.service.impl;

import com.tdtu.library.dto.OrderDto;
import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.*;
import com.tdtu.library.repository.CartRepository;
import com.tdtu.library.repository.ItemRepository;
import com.tdtu.library.repository.OrderDetailRepository;
import com.tdtu.library.repository.OrderRepository;
import com.tdtu.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ItemRepository itemRepo;

    @Override
    public void saveOrder(Cart cart) {
        Order order = new Order();
        order.setOrderStatus("PENDING");
        order.setOrderDate(new Date());
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrices());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        for (Item item : cart.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProduct(item.getProduct());
            orderDetail.setUnitPrice(item.getProduct().getPrice());
            orderDetailList.add(orderDetail);

            orderDetailRepo.save(orderDetail);
            itemRepo.delete(item);
        }
        order.setOrderDetails(orderDetailList);

        // Reset value
        cart.setItems(new HashSet<>());
        cart.setTotalItems(0);
        cart.setTotalPrices(0.0);

        cartRepo.save(cart);
        orderRepo.save(order);
    }

    @Override
    public void acceptOrder(Long id) {
        Order order = orderRepo.findById(id).get();
        order.setDeliveryDate(new Date());
        order.setOrderStatus("SHIPPING");
        orderRepo.save(order);
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepo.deleteById(id);
    }

    // ------


    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderRepo.findAll();
        return convert2OrderDtoList(orderList);
    }

    @Override
    public List<OrderDto> findAllByCustomerId(Long id) {
        List<Order> orderList = orderRepo.findAllByCustomerId(id);
        return convert2OrderDtoList(orderList);
    }

    private List<OrderDto> convert2OrderDtoList(List<Order> orderList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setNotes(order.getNotes());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setOrderStatus(order.getOrderStatus());
            orderDto.setShippingFee(order.getShippingFee());
            orderDto.setTaxFee(order.getTaxFee());
            orderDto.setDeliveryDate(order.getDeliveryDate());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
