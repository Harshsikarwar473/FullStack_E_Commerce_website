package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.response.Apiresponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping
    public Apiresponse greet(){
        Apiresponse apiresponse= new Apiresponse();
        apiresponse.setMessage("i am started");
        return apiresponse;
    }
}
