package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.model.CartItems;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.productservices.CartItemService;
import com.E_Commerce.Ecommerce.repo.CartItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartItemServiceimpl implements CartItemService {

    private final CartItemRepo cartItemRepo ;
    @Override
    public CartItems updateCartItem(Long userid, Long id, CartItems cartItems) {

        CartItems item = findCartItemById(id);

        User CartItemUser = item.getCart().getUser_name();
        if(CartItemUser.getId().equals(userid))
        return null;
    }

    @Override
    public void removeCartItem(Long id, Long cartItemId) {

    }

    @Override
    public CartItems findCartItemById(Long id) {
        return null;
    }
}
