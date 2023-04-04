package com.tdtu.library.service.impl;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Product;
import com.tdtu.library.repository.ProductRepository;
import com.tdtu.library.service.ProductService;
import com.tdtu.library.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ImageUpload imageUpload;

    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> products = repo.findAll();
        // Transfer product from DB to DTO. Then DTO transfer to View
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setBrand(product.getBrand());
            productDto.setColor(product.getColor());
            productDto.setCategory(product.getCategory());
            productDto.setDescription(product.getDescription());
            productDto.setSale(product.getSale());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setImage(product.getImage());
            productDto.setActivated(product.isActivated());
            productDto.setDeleted(product.isDeleted());
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public ProductDto findById(Long id) {
        ProductDto productDto = new ProductDto();
        Product product = repo.findById(id).get();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setBrand(product.getBrand());
        productDto.setColor(product.getColor());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setSale(product.getSale());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setImage(product.getImage());
        productDto.setActivated(product.isActivated());
        productDto.setDeleted(product.isDeleted());
        return productDto;
    }

    @Override
    public Product save(MultipartFile productImage, ProductDto productDto) {
        try {
            Product product = new Product();
            if (productImage == null) {
                product.setImage(null);
            } else {
                if (imageUpload.uploadImage(productImage)) {
                    System.out.println("Upload image successfully");
                }
                product.setImage(Base64.getEncoder().encodeToString(productImage.getBytes()));
            }
            product.setId(productDto.getId());
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setColor(productDto.getColor());
            product.setDescription(productDto.getDescription());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setSale(productDto.getSale());
            product.setActivated(true);
            product.setDeleted(false);

            return repo.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product update(MultipartFile productImage, ProductDto productDto) {
        try {
            Product product = repo.getReferenceById(productDto.getId());
            if (productImage == null) {
                product.setImage(product.getImage());
            } else {
                if (!imageUpload.isExistedImage(productImage)) {
                    imageUpload.uploadImage(productImage);
                    product.setImage(Base64.getEncoder().encodeToString(productImage.getBytes()));
                }
            }
            product.setName(product.getName());
            product.setPrice(productDto.getPrice());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setColor(productDto.getColor());
            product.setDescription(productDto.getDescription());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setSale(productDto.getSale());
            return repo.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Product product = repo.getReferenceById(id);
        product.setDeleted(true);
        product.setActivated(false);
        repo.save(product);
    }

    @Override
    public void enableById(Long id) {
        Product product = repo.getReferenceById(id);
        product.setActivated(true);
        product.setDeleted(false);
        repo.save(product);
    }

    @Override
    public Page<Product> pageProducts(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5); // 5 items per page
        Page<Product> productPages = repo.pageProduct(pageable);
        return productPages;
    }
}
