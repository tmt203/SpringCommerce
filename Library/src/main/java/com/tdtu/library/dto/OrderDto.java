package com.tdtu.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String notes;
    private String orderStatus;
    private double totalPrice;
    private double shippingFee;
    private double taxFee;
    private Date deliveryDate;
    private Date orderDate;
}
