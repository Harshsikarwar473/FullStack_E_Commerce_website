package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.model.WishList;

public interface WishListSercice {

    WishList createWishList(User user);
    WishList getWishListByUSerId(User user);
    WishList addProductToWishLIst(User user , Product product);
}
