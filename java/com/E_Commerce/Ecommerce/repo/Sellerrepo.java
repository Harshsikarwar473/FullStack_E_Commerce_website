package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Sellerrepo extends JpaRepository<Seller , Long > {
    Seller findByEmail(String email );
}
