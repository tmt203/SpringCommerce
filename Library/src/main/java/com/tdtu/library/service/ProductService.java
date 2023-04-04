package com.tdtu.library.service;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto findById(Long id);
    Product save(MultipartFile productImage, ProductDto productDto);
    Product update(MultipartFile productImage, ProductDto productDto);
    void deleteById(Long id);
    void enableById(Long id);

    Page<ProductDto> pageProducts(int pageNumber);
    Page<ProductDto> searchProducts(int pageNumber, String keyword);
}
