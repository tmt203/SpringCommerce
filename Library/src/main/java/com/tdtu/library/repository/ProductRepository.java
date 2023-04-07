package com.tdtu.library.repository;

import com.tdtu.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p")
    Page<Product> pageProduct(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    Page<Product> searchProducts(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> searchProductList(String keyword);

    @Query("SELECT p FROM Product p WHERE p.isActivated=true and p.isDeleted=false")
    List<Product> getAllProducts();

    @Query(value = "SELECT * FROM products WHERE is_activated=true and is_deleted=false order by rand() asc limit 4", nativeQuery = true)
    List<Product> listViewProducts();
}
