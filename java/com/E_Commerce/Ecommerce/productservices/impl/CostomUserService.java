package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.domin.User_Role;
import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.repo.Sellerrepo;
import com.E_Commerce.Ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CostomUserService implements UserDetailsService {

    private final  UserRepo userRepo ;
    private final Sellerrepo sellerrepo;
    private static final String SELLER_PREFIX = "seller";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username.startsWith(SELLER_PREFIX)){
          String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerrepo.findByEmail(actualUsername);
            if(seller!= null ){
                return buildUserDetails(seller.getEmail(), seller.getPassword() ,seller.getRole() );
            }
        }
        else{
            User user = userRepo.findByEmail(username);
            if(user!= null ){
                return buildUserDetails(user.getEmail(),user.getPassword() , user.getRole());
            }
        }
        throw new UsernameNotFoundException("user or seller not found with email " + username );
    }

    private UserDetails buildUserDetails(String email, String password, User_Role role) {
        if(role == null ) role = User_Role.ROLE_COSTUMER;

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(email,password , authorities);
    }
}
