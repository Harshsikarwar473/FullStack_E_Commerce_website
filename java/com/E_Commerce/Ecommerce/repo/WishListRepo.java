package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepo extends JpaRepository<WishList, Long > {
    WishList findByUserId(Long userId);
}
