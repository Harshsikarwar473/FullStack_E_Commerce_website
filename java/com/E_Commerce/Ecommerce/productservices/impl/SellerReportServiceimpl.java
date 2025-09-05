package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.model.SellerReport;
import com.E_Commerce.Ecommerce.productservices.SellerReportService;
import com.E_Commerce.Ecommerce.repo.SellerReportRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SellerReportServiceimpl implements SellerReportService {
   private final SellerReportRepo sellerReportRepo;
    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sr = sellerReportRepo.findBySellerId(seller.getId());

        if(sr==null){
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepo.save(newReport);
        }
        return sr;
    }

    @Override
    public SellerReport updateSellerreport(SellerReport sellerReport) {
        return sellerReportRepo.save(sellerReport);
    }
}
