package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.model.CartItems;

public interface CartItemService {

    CartItems updateCartItem( Long userid  , Long id , CartItems cartItems) throws Exception;
    void removeCartItem(Long id , Long cartItemId) throws Exception;
    CartItems findCartItemById(Long id ) throws Exception;
}
