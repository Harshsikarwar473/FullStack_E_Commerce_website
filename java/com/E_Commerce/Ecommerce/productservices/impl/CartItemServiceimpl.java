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
    public CartItems updateCartItem(Long userid, Long id, CartItems cartItems) throws Exception {

        CartItems item = findCartItemById(id);

        User cartItemUser = item.getCart().getUser();
        if(cartItemUser.getId()==userid){
            item.setQuantity(item.getQuantity()+cartItems.getQuantity());
            item.setMrp(item.getQuantity()*item.getProduct().getMrpprice());
            item.setSellingptice(item.getQuantity()*item.getProduct().getSellingPrice());

            return cartItemRepo.save(item);
        }
            throw new Exception("you cant update  this user cart item");


    }

    @Override
    public void removeCartItem(Long id, Long cartItemId) throws Exception {

        CartItems item = findCartItemById(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if(cartItemUser.getId()==id){
            cartItemRepo.delete(item);
        }
        else throw new Exception("you cant remove from this cart");

    }

    @Override
    public CartItems findCartItemById(Long id) throws Exception {
        return cartItemRepo.findById(id).orElseThrow(()->
                new Exception("cart item not found "));

    }
}
