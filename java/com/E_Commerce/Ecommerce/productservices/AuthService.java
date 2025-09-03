package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.domin.User_Role;
import com.E_Commerce.Ecommerce.request.LoginRequest;
import com.E_Commerce.Ecommerce.response.Authresponse;
import com.E_Commerce.Ecommerce.response.SignupRequest;

public interface AuthService {
   void sendloginotp(String email, User_Role role) throws Exception;
    String createUser (SignupRequest req) throws Exception;
    Authresponse signin(LoginRequest req);


}
