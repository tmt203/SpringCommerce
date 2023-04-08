package com.tdtu.library.repository;

import com.tdtu.library.model.Order;
import com.tdtu.library.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
