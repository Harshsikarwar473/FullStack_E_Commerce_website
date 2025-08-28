package com.E_Commerce.Ecommerce.response;

import com.E_Commerce.Ecommerce.domin.User_Role;
import lombok.Data;


@Data
public class Authresponse {
    private String jwt ;
    private String message ;
    private User_Role role ;
}
