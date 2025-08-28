package com.E_Commerce.Ecommerce.request;


import lombok.Data;

@Data
public class LoginRequest {
    private  String email;
    private String otp;
}
