package com.E_Commerce.Ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartItems {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    @ManyToOne
    @JsonIgnore
    private  Cart cart ;
    @ManyToOne
    private Product product;

    private String size ;

    private int  Quantity = 1 ;

    private Integer mrp ;

    private Integer sellingptice ;

    private long userid ;





}
