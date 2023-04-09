package com.tdtu.library.repository;

import com.tdtu.library.dto.OrderDto;
import com.tdtu.library.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders", nativeQuery = true)
    List<Order> findAll();
    List<Order> findAllByCustomerId(Long id);
}
