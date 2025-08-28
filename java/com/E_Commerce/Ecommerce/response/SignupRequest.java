package com.E_Commerce.Ecommerce.response;


import lombok.Data;


@Data
public class SignupRequest {

    private String email ;
    private String fullname ;
    private String mobile  ;
    private String otp ;


}
