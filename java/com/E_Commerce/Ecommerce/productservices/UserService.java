package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.model.User;

public interface UserService {

    User finduserbyJwttoken(String jwt) throws Exception;
     User findUserbyEmail(String email) throws Exception;
}
