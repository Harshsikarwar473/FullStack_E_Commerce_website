package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Order;
import com.E_Commerce.Ecommerce.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Long > {
}
