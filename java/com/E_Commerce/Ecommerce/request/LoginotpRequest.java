package com.E_Commerce.Ecommerce.request;

import com.E_Commerce.Ecommerce.domin.User_Role;
import lombok.Data;


@Data
public class LoginotpRequest {

   private  String email ;
   private  String otp ;
    private User_Role role ;
}
