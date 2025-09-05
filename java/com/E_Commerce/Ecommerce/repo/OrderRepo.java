package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long > {

    List<Order> findByuserId(Long userId);
    List<Order> findBySellerId(Long sellerId);
}
