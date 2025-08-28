package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.config.JwtProvider;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.productservices.UserService;
import com.E_Commerce.Ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtProvider jwtProvider;
    @Override
    public User finduserbyJwttoken(String jwt) throws Exception {
        String email =jwtProvider.getEmailFromJwtToken(jwt);

     User user = this.findUserbyEmail(email);

        return user ;
    }

    @Override
    public User findUserbyEmail(String email) throws Exception {

        User user = userRepo.findByEmail(email);

        if(user==null){
            throw new Exception("User not found with this email : "+email);
        }
        return user;
    }
}
