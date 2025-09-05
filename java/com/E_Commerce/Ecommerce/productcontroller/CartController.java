package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.model.Cart;
import com.E_Commerce.Ecommerce.model.CartItems;
import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.productservices.CartItemService;
import com.E_Commerce.Ecommerce.productservices.CartService;
import com.E_Commerce.Ecommerce.productservices.ProductService;
import com.E_Commerce.Ecommerce.productservices.UserService;
import com.E_Commerce.Ecommerce.request.AddItemRequest;
import com.E_Commerce.Ecommerce.response.Apiresponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.finduserbyJwttoken(jwt);

        Cart cart = cartService.findUserCart(user);

        return  new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItems> additemtoCart(@RequestBody AddItemRequest req ,
                                                   @RequestHeader("Authorization") String jwt ) throws Exception {

        User user = userService.finduserbyJwttoken(jwt);
        Product product = productService.FindProductById((req.getProductId()));

        CartItems items = cartService.addCartItem(user , product , req.getSize(), req.getQuantity());

        Apiresponse res = new Apiresponse();
        res.setMessage("item added to cart successfully");

        return new ResponseEntity<>(items , HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<Apiresponse> deleteCartItemHandler(
            @PathVariable Long cartItemId ,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.finduserbyJwttoken(jwt);
        cartItemService.removeCartItem(user.getId() , cartItemId);

        Apiresponse res = new Apiresponse();
                res.setMessage("item removed");

                return new ResponseEntity<Apiresponse>(res , HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<CartItems> updateCartItem(
            @PathVariable Long id ,
            @RequestBody CartItems cartItems,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.finduserbyJwttoken(jwt);

        CartItems updatedCartItem = null;

        if(cartItems.getQuantity()>0){
            updatedCartItem = cartItemService.updateCartItem(user.getId(), id, cartItems);
        }

        return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);

    }

}
