package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review , Long> {
    List<Review> findByProductId(Long productId);
}
