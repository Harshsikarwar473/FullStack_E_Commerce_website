package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.repo.UserRepo;
import com.E_Commerce.Ecommerce.response.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

   private  final UserRepo userRepo;

    @PostMapping("/SignUp")
   public ResponseEntity<User> createUserHandler( @RequestBody  SignupRequest req){
     User user =new User();
     user.setEmail(req.getEmail());
     user.setFullName(req.getFullname());

    User savedUser = userRepo.save(user);
     return ResponseEntity.ok(savedUser);

   }


}
