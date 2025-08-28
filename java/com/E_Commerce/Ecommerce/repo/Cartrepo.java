package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cartrepo extends JpaRepository<Cart, Long> {
}
