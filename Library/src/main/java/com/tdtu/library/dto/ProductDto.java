package com.tdtu.library.dto;

import com.tdtu.library.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String brand;
    private String color;
    private Category category;
    private String description;
    private double sale;
    private int currentQuantity;
    private String image;
    private boolean isActivated;
    private boolean isDeleted;
}
