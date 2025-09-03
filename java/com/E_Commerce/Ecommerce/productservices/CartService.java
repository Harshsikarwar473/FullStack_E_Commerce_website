package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.model.Cart;
import com.E_Commerce.Ecommerce.model.CartItems;
import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.User;

public interface CartService {

    public CartItems addCartItem(
            User user,
            Product product,
            String size ,
            int quantity
    );
    public Cart findUserCart(User user);
}
