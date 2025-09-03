package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.domin.AccountStatus;
import com.E_Commerce.Ecommerce.exception.SellerException;
import com.E_Commerce.Ecommerce.model.Seller;

import java.util.List;

public interface SellerService {
    Seller getSellerprofile(String jwt) throws Exception;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerbyid (Long id ) throws SellerException;
    Seller getSellerbyEmail(String email) throws Exception;
//    List<Seller> getAllSellers(AccountStatus status);
    Seller updateSeller(Long id , Seller seller) throws Exception;
    void deleteSeller(Long id ) throws Exception;
    Seller verifyEmail(String email , String otp ) throws Exception;
    Seller updateSellerAccountStatus( Long SellerId , AccountStatus status) throws Exception;
}
