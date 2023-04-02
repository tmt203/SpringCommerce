package com.tdtu.library.service;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    Product save(ProductDto product);
    Product update(ProductDto product);
    void deleteById(Long id);
    void enableById(Long id);
}
