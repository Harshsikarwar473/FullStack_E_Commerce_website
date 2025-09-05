package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Cart;
import com.E_Commerce.Ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Cartrepo extends JpaRepository<Cart, Long> {

    Cart  findByUser(User user );

}
