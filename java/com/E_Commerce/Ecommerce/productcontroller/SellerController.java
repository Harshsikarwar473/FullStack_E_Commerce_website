package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.config.JwtProvider;
import com.E_Commerce.Ecommerce.exception.SellerException;
import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.model.SellerReport;
import com.E_Commerce.Ecommerce.model.Verificationcode;
import com.E_Commerce.Ecommerce.productservices.AuthService;
import com.E_Commerce.Ecommerce.productservices.EmailService;
import com.E_Commerce.Ecommerce.productservices.SellerReportService;
import com.E_Commerce.Ecommerce.productservices.SellerService;
import com.E_Commerce.Ecommerce.repo.VerificationCodeRepo;
import com.E_Commerce.Ecommerce.request.LoginRequest;
import com.E_Commerce.Ecommerce.request.LoginotpRequest;
import com.E_Commerce.Ecommerce.response.Apiresponse;
import com.E_Commerce.Ecommerce.response.Authresponse;
import com.E_Commerce.Ecommerce.util.Otputil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;
    public final VerificationCodeRepo verificationCodeRepo;
    private  final AuthService authService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
    private final SellerReportService sellerReportService;


//


    @PostMapping("/login")
    public ResponseEntity<Authresponse> LoginSeller(
            @RequestBody LoginRequest req
            ) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();

//
        req.setEmail("seller"+email);
        Authresponse authresponse = authService.signin(req);

        return  ResponseEntity.ok(authresponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp ) throws Exception {
        Verificationcode verificationcode = verificationCodeRepo.findByOtp(otp);

        if(verificationcode==null || !verificationcode.getOtp().equals(otp)){
            throw new Exception("wrong otp...");
        }
        Seller seller = sellerService.verifyEmail(verificationcode.getEmail(),otp);


        return new ResponseEntity<>(seller, HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        Seller savedSeller = sellerService.createSeller(seller);

        String otp = Otputil.generateotp();
        Verificationcode verificationcode = new Verificationcode();
        verificationcode.setOtp(otp);
        verificationcode.setEmail(seller.getEmail());
        verificationCodeRepo.save(verificationcode);


        String subject = "Ecomerce email verification code";
        String Text= "Welcome to Ecommerce , verify your account using this link ";
        String frontend_link = "";
        emailService.sendverificationotpEmail(seller.getEmail(), verificationcode.getOtp(),subject,Text + frontend_link);


        return  new ResponseEntity<>(savedSeller,HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
            public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerbyid(id);

        return new ResponseEntity<>(seller,HttpStatus.OK);

    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerbyJwt(@RequestHeader("Authorization") String jwt) throws Exception {

        Seller seller = sellerService.getSellerprofile(jwt);

        return new ResponseEntity<>(seller,HttpStatus.OK);

    }

    @GetMapping("/report")
    public  ResponseEntity<SellerReport> getSellerReport(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Seller seller = sellerService.getSellerprofile(jwt);
        SellerReport report = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt , @RequestBody Seller seller) throws Exception {
        Seller profile = sellerService.getSellerprofile(jwt);
        Seller updateSeller = sellerService.updateSeller(profile.getId(),seller);

        return ResponseEntity.ok(updateSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellerService.deleteSeller(id);

        return ResponseEntity.noContent().build();
    }



}
