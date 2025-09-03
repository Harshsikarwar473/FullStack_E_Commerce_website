package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Cart;
import com.E_Commerce.Ecommerce.model.CartItems;
import com.E_Commerce.Ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItems , Long> {
CartItems findByCartAndProductAndSize(Cart cart , Product product , String size );
}
