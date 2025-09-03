package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Categoryrepo extends JpaRepository<Category , Long> {
    Category findByCatagoryId(String id );
}
