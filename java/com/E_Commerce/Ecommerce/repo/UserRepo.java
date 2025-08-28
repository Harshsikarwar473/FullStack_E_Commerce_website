package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long > {

  User findByEmail(String email );
}
