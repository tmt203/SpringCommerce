package com.tdtu.api.controller;

import com.tdtu.library.dto.OrderDto;
import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Order;
import com.tdtu.library.model.Product;
import com.tdtu.library.repository.ProductRepository;
import com.tdtu.library.service.OrderService;
import com.tdtu.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RestAPIController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    // Products

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        try {
            ProductDto productDto = productService.findById(id);
            return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.save(null, productDto);
    }

    @PutMapping("/products")
    public ResponseEntity<?> updateProductById(@RequestBody ProductDto productDto) {
        try {
            productService.findById(productDto.getId());
            productService.update(null, productDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        try {
            productService.findById(id);
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Orders

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public List<OrderDto> getOrderByCustomerId(@PathVariable("id") Long id) {
        return orderService.findAllByCustomerId(id);
    }
}
