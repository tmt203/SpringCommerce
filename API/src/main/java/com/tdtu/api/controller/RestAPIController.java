package com.tdtu.api.controller;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Product;
import com.tdtu.library.repository.ProductRepository;
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

    @GetMapping("/products")
    public List<Product> products() {
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
    public void addProduct(@RequestBody ProductDto productDto, @RequestParam("productImage") MultipartFile productImage) {
        productService.save(productImage, productDto);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(
            @RequestBody ProductDto productDto,
            @RequestParam("productImage") MultipartFile productImage,
            @PathVariable("id") Long id) {
        try {
            productService.findById(id);
            productService.update(productImage, productDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
