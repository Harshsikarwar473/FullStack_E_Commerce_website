package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.productservices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> createUserHandler(@RequestHeader("Authorization") String jwt) throws Exception {


        User user =userService.finduserbyJwttoken(jwt);
        return ResponseEntity.ok(user);

    }

}
