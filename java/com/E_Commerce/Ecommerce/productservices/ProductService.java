package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.exception.ProductException;
import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;
//import org.hibernate.query.Page;

import java.util.List;
//import org.hibernate.mapping.List;

public interface ProductService {

    public Product createProduct(CreateProductRequest req , Seller seller);
    public void DeleteProducts(Long id ) throws ProductException;
    public Product UpdateProduct(Long id , Product product) throws ProductException;
    Product FindProductById(Long id ) throws ProductException;
    List<Product> SearchProduct(String query);

    public Page<Product> getAllProducts(
            String category,
            String brand ,
            String color,
            String size ,
            Integer minprice ,
            Integer maxprice,
            Integer mindescount,
            String sort,
            String stock,
            Integer pagenumber
    );

    List<Product> getProductBySellerId(Long id);
}
