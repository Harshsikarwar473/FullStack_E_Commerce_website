package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.config.JwtProvider;
import com.E_Commerce.Ecommerce.domin.AccountStatus;
import com.E_Commerce.Ecommerce.domin.User_Role;
import com.E_Commerce.Ecommerce.exception.SellerException;
import com.E_Commerce.Ecommerce.model.Address;
import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.productservices.SellerService;
import com.E_Commerce.Ecommerce.repo.Addressrepo;
import com.E_Commerce.Ecommerce.repo.Sellerrepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

     @Autowired
    Sellerrepo sellerrepo;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final Addressrepo addressrepo;

    @Override
    public Seller getSellerprofile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerbyEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExists =sellerrepo.findByEmail(seller.getEmail());
        if(sellerExists!=null){
            throw new Exception("seller already exsists use another email...");
        }
        Address savedAddress = addressrepo.save(seller.getPickupAddress());


        Seller newSeller = new Seller();
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setName(seller.getName());
        newSeller.setPickupAddress(savedAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(User_Role.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());




        return sellerrepo.save(newSeller);
    }

    @Override
    public Seller getSellerbyid(Long id) throws SellerException {

        return sellerrepo.findById(id).orElseThrow(()->new SellerException("seller not found by given id ....." +id));

    }

    @Override
    public Seller getSellerbyEmail(String email) throws Exception {
        Seller seller= sellerrepo.findByEmail(email);
        if(seller==null){
            throw new Exception("Seller not found.....");
        }
        return seller;
    }

//    @Override
//    public List<Seller> getAllSellers(AccountStatus status) {
//        return sellerrepo.findbyAcountStatus(status);
//    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        Seller existSeller = sellerrepo.findById(id)
                .orElseThrow(() ->
                        new Exception("Seller not found with id "  +id));
        if(seller.getName()!=null){
            existSeller.setName(seller.getName());
        }
        if(seller.getMobile()!=null){
            existSeller.setMobile(seller.getMobile());
        }
        if(seller.getEmail()!=null){
            existSeller.setEmail(seller.getEmail());
        }
        if(seller.getBusinessDetails()!=null && seller.getBusinessDetails().getBusinessname()!=null){
            existSeller.getBusinessDetails().setBusinessname(seller.getBusinessDetails().getBusinessname());
        }
        if(seller.getBankDetails()!=null
        && seller.getBankDetails().getAcountholder()!=null
        &&seller.getBankDetails().getIsfcade()!=null
        &&seller.getBankDetails().getAcountnumber()!=null){
            existSeller.getBankDetails().setAcountholder(
                    seller.getBankDetails().getAcountholder()
            );

            existSeller.getBankDetails().setAcountnumber(
                    seller.getBankDetails().getAcountnumber()
            );

            existSeller.getBankDetails().setIsfcade(
                    seller.getBankDetails().getIsfcade()
            );
        }

        if(seller.getPickupAddress()!=null
        &&seller.getPickupAddress().getAddress()!=null
        &&seller.getPickupAddress().getCity()!=null
        &&seller.getPickupAddress().getState()!=null
        &&seller.getPickupAddress().getLocality()!=null
        &seller.getPickupAddress().getPincode()!=null
        &&seller.getPickupAddress().getMobileNo()!=null){

            existSeller.getPickupAddress().setAddress(
                    seller.getPickupAddress().getAddress()
            );

            existSeller.getPickupAddress().setCity(
                    seller.getPickupAddress().getCity()
            );
            existSeller.getPickupAddress().setState(
                    seller.getPickupAddress().getState()
            );

            existSeller.getPickupAddress().setLocality(
                    seller.getPickupAddress().getLocality()
            );

            existSeller.getPickupAddress().setPincode(
                    seller.getPickupAddress().getPincode()
            );

            existSeller.getPickupAddress().setMobileNo(
                    seller.getPickupAddress().getMobileNo()
            );

        }

        if(seller.getGSTIN()!=null){
            existSeller.setGSTIN(seller.getGSTIN());
        }

        return sellerrepo.save(existSeller);

    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = getSellerbyid(id);
        sellerrepo.delete(seller);


    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller =  getSellerbyEmail(email);
        seller.setEmailverified(true);

        return sellerrepo.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(Long SellerId, AccountStatus status) throws Exception {
        Seller seller = getSellerbyid(SellerId);
        seller.setAccountStatus(status);
        return sellerrepo.save(seller);
    }
}
