package com.E_Commerce.Ecommerce.productcontroller;

import com.E_Commerce.Ecommerce.exception.ProductException;
import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.model.WishList;
import com.E_Commerce.Ecommerce.productservices.ProductService;
import com.E_Commerce.Ecommerce.productservices.UserService;
import com.E_Commerce.Ecommerce.productservices.WishListSercice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListSercice wishListSercice;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<WishList> getWishListByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user = userService.finduserbyJwttoken(jwt);
        WishList wishList = wishListSercice.getWishListByUSerId(user);
        return ResponseEntity.ok(wishList);
    }
    @PutMapping("/add-product/{productId}")
    public ResponseEntity<WishList> addProducttoWishList(
            @PathVariable Long productId,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        Product product = productService.FindProductById(productId);
        User user = userService.finduserbyJwttoken(jwt);
        WishList updatedWishList = wishListSercice.addProductToWishLIst(user , product);

        return  ResponseEntity.ok(updatedWishList);
    }
}
