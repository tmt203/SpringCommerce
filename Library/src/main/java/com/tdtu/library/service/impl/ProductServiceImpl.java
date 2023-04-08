package com.tdtu.library.service.impl;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Product;
import com.tdtu.library.repository.ProductRepository;
import com.tdtu.library.service.ProductService;
import com.tdtu.library.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    // Admin
    @Override
    public List<ProductDto> findAll() {
        List<Product> products = repo.findAll();
        // Transfer product from DB to DTO. Then DTO transfer to View
        return convert2ProductDtoList(products);
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
    public Page<ProductDto> pageProducts(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5); // 5 items per page
        List<ProductDto> productDtoList = convert2ProductDtoList(repo.findAll());
        Page<ProductDto> productPages = toPage(productDtoList, pageable);
        return productPages;
    }

    @Override
    public Page<ProductDto> searchProducts(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        List<ProductDto> productDtoList = convert2ProductDtoList(repo.searchProductList(keyword));
        Page<ProductDto> products = toPage(productDtoList, pageable);
        return products;
    }

    private Page toPage(List<ProductDto> productDtoList, Pageable pageable) {
        // Check if the request page beyond the range of the available results
        if (pageable.getOffset() >= productDtoList.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min((int) pageable.getOffset() + pageable.getPageSize(), productDtoList.size());
        List subProductDtoList = productDtoList.subList(startIndex, endIndex);
        return new PageImpl(subProductDtoList, pageable, productDtoList.size());
    }

    private List<ProductDto> convert2ProductDtoList(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
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

    // Customer
    @Override
    public List<Product> getAllProducts() {
        return repo.getAllProducts();
    }

    @Override
    public List<Product> listViewProducts() {
        return repo.listViewProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Product> getRelatedProductsById(Long id) {
        return repo.getRelatedProductsById(id);
    }

    @Override
    public List<Product> getProductsInCategory(Long categoryId) {
        return repo.getProductsInCategory(categoryId);
    }

    @Override
    public List<Product> filterProductsByPriceAsc() {
        return repo.filterProductsByPriceAsc();
    }

    @Override
    public List<Product> filterProductsByPriceDesc() {
        return repo.filterProductsByPriceDesc();
    }
}
