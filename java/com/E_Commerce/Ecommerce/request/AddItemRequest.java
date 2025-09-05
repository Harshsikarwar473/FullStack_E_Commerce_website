package com.E_Commerce.Ecommerce.request;


import lombok.Data;

@Data
public class AddItemRequest {
    private String size ;
    private int Quantity;
    private Long  productId;
}
