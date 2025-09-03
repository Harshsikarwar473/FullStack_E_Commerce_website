package com.E_Commerce.Ecommerce.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {
    private String tittle ;
    private String description;
    private  int mrp ;
    private int sellingprice ;
    private String color ;
    private List<String> images ;
    private String category;
    private String category2;
    private String category3;
    private Integer numrating;
    private String size ;
    private Integer quantity ;


}
