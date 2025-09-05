package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.SellerReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerReportRepo extends JpaRepository<SellerReport,Long> {
    SellerReport findBySellerId(Long SellerId);
}
