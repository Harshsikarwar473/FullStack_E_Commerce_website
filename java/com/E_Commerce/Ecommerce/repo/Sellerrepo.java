package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.domin.AccountStatus;
import com.E_Commerce.Ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Sellerrepo extends JpaRepository<Seller , Long > {
    Seller findByEmail(String email );
//    List<Seller> findbyAcountStatus(AccountStatus status);
}
