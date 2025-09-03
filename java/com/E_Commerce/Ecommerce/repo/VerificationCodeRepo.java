package com.E_Commerce.Ecommerce.repo;

import com.E_Commerce.Ecommerce.model.Verificationcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepo extends JpaRepository<Verificationcode , Long> {

    Verificationcode findByEmail(String email);
    Verificationcode findByOtp(String otp);
}
