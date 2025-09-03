package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.model.Cart;
import com.E_Commerce.Ecommerce.model.CartItems;
import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.productservices.CartService;
import com.E_Commerce.Ecommerce.repo.CartItemRepo;
import com.E_Commerce.Ecommerce.repo.Cartrepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepo cartItemRepo;
    private final Cartrepo cartrepo;

    @Override
    public CartItems addCartItem(User user, Product product, String size, int quantity) {
     Cart cart = findUserCart(user);

     CartItems isPresent =cartItemRepo.findByCartAndProductAndSize(cart , product , size );
     if(isPresent==null){
         CartItems cartItems= new CartItems();
         cartItems.setProduct(product);
         cartItems.setQuantity(quantity);
         cartItems.setUserid(user.getId());
         cartItems.setSize(size);

         int totalPrice = quantity* product.getSellingPrice();
         cartItems.setSellingptice(totalPrice);


         cart.getCartItems().add(cartItems);
         cartItems.setCart(cart);

         return cartItemRepo.save(cartItems);
     }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartrepo.findByUserId(user.getId());


        int totalprice = 0 ;
        int total_discountPrice = 0 ;
        int totalItem = 0 ;

        for(CartItems cartItems : cart.getCartItems()){
            totalprice+=cartItems.getMrp();
            total_discountPrice+=cartItems.getSellingptice();
            totalItem+=cartItems.getQuantity();
        }

        cart.setTotalMrpPrice(totalprice);
        cart.setTotalitem(totalItem);
        cart.setTotalsellingprice(total_discountPrice);
        cart.setDiscount(calculateDiscountPercentage(totalprice , total_discountPrice));
        return cart;
    }
    private int calculateDiscountPercentage(int mrp, int sellingprice) {

        if(mrp == 0 ){
            throw new IllegalArgumentException("mrp should be gretter than zero ");
        }
        double dis = mrp-sellingprice;
        double percent = (dis / mrp)*100;
        return  (int) percent;
    }

}
