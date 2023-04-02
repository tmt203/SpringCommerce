package com.tdtu.library.service.impl;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Product;
import com.tdtu.library.repository.ProductRepository;
import com.tdtu.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> products = repo.findAll();
        // Transfer product from DB to DTO. Then DTO transfer to View
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setCost(product.getCost());
            productDto.setSale(product.getSale());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setImage(productDto.getImage());
            productDto.setCategory(productDto.getCategory());
            productDto.set_activated(productDto.is_activated());
            productDto.set_deleted(productDto.is_deleted());
        }
        return productDtoList;
    }

    @Override
    public Product save(ProductDto product) {
        return null;
    }

    @Override
    public Product update(ProductDto product) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void enableById(Long id) {

    }
}
