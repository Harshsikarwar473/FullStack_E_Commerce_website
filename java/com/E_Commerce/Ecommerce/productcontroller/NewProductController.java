package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.exception.ProductException;
import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.productservices.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class NewProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getproductbyId(
            @PathVariable Long id
    ) throws ProductException {
        Product product= productService.FindProductById(id);
        return  new ResponseEntity<>(product, HttpStatus.OK);
    }
@GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(
            @RequestParam(required = false) String query
    ){
        List<Product> products =productService.SearchProduct(query);
        return new ResponseEntity<>(products , HttpStatus.OK);
    }
@GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(required = false)String category,
            @RequestParam(required = false) String brand ,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Integer minprice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false)  Integer mindiscount,
            @RequestParam(required = false)  String sort ,
            @RequestParam(required = false)  String stock,
            @RequestParam(defaultValue = "0") Integer pagenumber
    ){
        return  new ResponseEntity<>(productService.getAllProducts(
                category, brand, color, size, minprice, maxPrice, mindiscount, sort, stock, pagenumber) , HttpStatus.OK);
    }

}
