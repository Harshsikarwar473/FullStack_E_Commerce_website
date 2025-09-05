package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.model.SellerReport;

public interface SellerReportService {

    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerreport(SellerReport sellerReport);

}
