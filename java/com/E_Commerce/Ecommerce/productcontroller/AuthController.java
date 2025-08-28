package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.domin.User_Role;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.model.Verificationcode;
import com.E_Commerce.Ecommerce.productservices.AuthService;
import com.E_Commerce.Ecommerce.repo.UserRepo;
import com.E_Commerce.Ecommerce.request.LoginRequest;
import com.E_Commerce.Ecommerce.response.Apiresponse;
import com.E_Commerce.Ecommerce.response.Authresponse;
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
   private final AuthService authService ;

    @PostMapping("/SignUp")
   public ResponseEntity<Authresponse> createUserHandler( @RequestBody  SignupRequest req) throws Exception {
    String jwt = authService.createUser(req);
        Authresponse response = new Authresponse();
        response.setJwt(jwt);
        response.setMessage("registered successfully");
        response.setRole(User_Role.ROLE_COSTUMER);
     return ResponseEntity.ok(response);

   }
    @PostMapping("/sent-otp/login")
    public ResponseEntity<Apiresponse> sentotpHandler(@RequestBody Verificationcode req) throws Exception {
          authService.sendloginotp(req.getEmail());
       Apiresponse response = new Apiresponse();

        response.setMessage("otp sent successfully ");

        return ResponseEntity.ok(response);

    }

    @PostMapping("/Signin")
    public ResponseEntity<Authresponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

        Authresponse response =  authService.signin(req);


        return ResponseEntity.ok(response);

    }


}
