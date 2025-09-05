package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.productservices.ProductService;
import com.E_Commerce.Ecommerce.productservices.ReviewService;
import com.E_Commerce.Ecommerce.productservices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

}
