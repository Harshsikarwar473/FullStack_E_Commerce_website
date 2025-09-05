package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.model.WishList;
import com.E_Commerce.Ecommerce.productservices.WishListSercice;
import com.E_Commerce.Ecommerce.repo.WishListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WishListServiceimpl implements WishListSercice {
    private final WishListRepo wishListRepo;
    @Override
    public WishList createWishList(User user) {

        WishList wishList = new WishList();
        wishList.setUser(user);

        return wishListRepo.save(wishList);
    }

    @Override
    public WishList getWishListByUSerId(User user) {
        WishList wishList = wishListRepo.findByUserId(user.getId());
        if (wishList==null){
            wishList= createWishList(user);
        }
        return wishList;

    }

    @Override
    public WishList addProductToWishLIst(User user , Product product) {
        WishList wishList = getWishListByUSerId(user);
        if(wishList.getProduct().contains(product)){
            wishList.getProduct().remove(product);
        }
        else{
            wishList.getProduct().add(product);
        }
        return wishList;
    }
}
