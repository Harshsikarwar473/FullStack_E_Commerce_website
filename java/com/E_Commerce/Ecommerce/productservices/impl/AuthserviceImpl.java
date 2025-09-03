package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.config.JwtProvider;
import com.E_Commerce.Ecommerce.domin.User_Role;
import com.E_Commerce.Ecommerce.model.Cart;
import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.model.Verificationcode;
import com.E_Commerce.Ecommerce.productservices.AuthService;
import com.E_Commerce.Ecommerce.productservices.EmailService;
import com.E_Commerce.Ecommerce.repo.Cartrepo;
import com.E_Commerce.Ecommerce.repo.Sellerrepo;
import com.E_Commerce.Ecommerce.repo.UserRepo;
import com.E_Commerce.Ecommerce.repo.VerificationCodeRepo;
import com.E_Commerce.Ecommerce.request.LoginRequest;
import com.E_Commerce.Ecommerce.response.Authresponse;
import com.E_Commerce.Ecommerce.response.SignupRequest;
import com.E_Commerce.Ecommerce.util.Otputil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthserviceImpl implements AuthService {

    private final UserRepo userRepo;
    private final Sellerrepo sellerrepo;
    private final PasswordEncoder passwordEncoder;
    private final Cartrepo cartrepo;
    private final JwtProvider jwtProvider ;
    private final VerificationCodeRepo verificationCodeRepo ;
    private final EmailService emailService;
    private final CostomUserService costomUserService;

    @Override
    public void sendloginotp(String email, User_Role role ) throws Exception {
        String SIGNING_PREFIX =  "signing_";

        if(email.startsWith(SIGNING_PREFIX)){
            email = email.substring(SIGNING_PREFIX.length());

            if(role.equals(User_Role.ROLE_SELLER)){
                Seller seller= sellerrepo.findByEmail(email);
                if(seller==null){
                    throw new Exception("seller not found .....");
                }


            }

            else{

                User user = userRepo.findByEmail(email);
                if(user==null){
                    throw new Exception("user not Exists with that email " +email);
                }
            }


        }
        Verificationcode isExist = verificationCodeRepo.findByEmail(email);
        if(isExist!= null ){
            verificationCodeRepo.delete(isExist);
        }

        String otp = Otputil.generateotp();
        Verificationcode verificationcode = new Verificationcode();
        verificationcode.setOtp(otp);
        verificationcode.setEmail(email);
        verificationCodeRepo.save(verificationcode);


        String subject = "site Signup / login otp ";
        String text =  " your otp is "+otp;

        emailService.sendverificationotpEmail(email,otp,subject,text);

    }

    @Override
    public String createUser(SignupRequest req) throws Exception {

        Verificationcode verificationcode = verificationCodeRepo.findByEmail(req.getEmail());
        if(verificationcode==null|| !verificationcode.getOtp().equals(req.getOtp())){
            throw new Exception("wrong otp...");
        }



     User user = userRepo.findByEmail(req.getEmail());


     if(user==null){
         User createuser =new User();
         createuser.setEmail(req.getEmail());
         createuser.setFullName(req.getFullname());
         createuser.setMobile(req.getMobile());
         createuser.setPassword(passwordEncoder.encode(req.getOtp()));
         createuser.setRole(User_Role.ROLE_COSTUMER);

         user = userRepo.save(createuser);
         Cart cart=new Cart();
         cart.setUser_name(user);
         cartrepo.save(cart);
     }
     List<GrantedAuthority> authorities = new ArrayList<>();
     authorities.add(new SimpleGrantedAuthority(User_Role.ROLE_COSTUMER.toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail() , null ,authorities );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return jwtProvider.generatetoken(authentication);
    }

    @Override
    public Authresponse signin(LoginRequest req) {
        String username = req.getEmail();
        String otp = req.getOtp();


        Authentication authentication= authenticate (username , otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generatetoken(authentication);

        Authresponse authresponse= new Authresponse();
        authresponse.setJwt(token);
        authresponse.setMessage("Login successfully");


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();


        authresponse.setRole(User_Role.valueOf(role));

        return authresponse;
    }

    private Authentication authenticate(String username, String otp) {
       UserDetails userDetails  =  costomUserService.loadUserByUsername(username);


        String SELLER_PREFIX = "seller";
        if(username.startsWith(SELLER_PREFIX)){
            username=username.substring(SELLER_PREFIX.length());

        }

       if(userDetails==null){
           throw new BadCredentialsException("invalid  username ");

       }
       Verificationcode verificationcode = verificationCodeRepo.findByEmail(username);
       if(verificationcode==null || !verificationcode.getOtp().equals(otp)){
           throw new BadCredentialsException("invalid otp");
       }


        return new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
    }
}
