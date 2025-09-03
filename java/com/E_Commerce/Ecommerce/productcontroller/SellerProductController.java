package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.exception.ProductException;
import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.productservices.ProductService;
import com.E_Commerce.Ecommerce.productservices.SellerService;
import com.E_Commerce.Ecommerce.request.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers/products")
public class SellerProductController {

    private  final SellerService sellerService;
    private final ProductService productService;
@GetMapping
    public ResponseEntity<List<Product>> getProductBySellerId(
            @RequestHeader("Authorization") String jwt
) throws Exception {
    Seller seller = sellerService.getSellerprofile(jwt);

    List<Product> products = productService.getProductBySellerId(seller.getId());
    return  new ResponseEntity<>(products, HttpStatus.OK);

}

@PostMapping
    public ResponseEntity<Product> createProduct(
        @RequestBody CreateProductRequest request,
        @RequestHeader("Authorization") String jwt
        ) throws Exception {
    Seller seller = sellerService.getSellerprofile(jwt);

    Product product = productService.createProduct(request,seller);
    return new ResponseEntity<>(product,HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteProduct(@PathVariable Long id){
    try{
        productService.DeleteProducts(id);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (ProductException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id ,
                                                 @RequestBody Product product){
    try{
        Product updateProduct = productService.UpdateProduct(id, product);

        return  new ResponseEntity<>(updateProduct,HttpStatus.OK);
    } catch (ProductException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

}
