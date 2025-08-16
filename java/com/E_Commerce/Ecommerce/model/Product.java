package com.E_Commerce.Ecommerce.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  long id ;


    private String tittle ;

    private String Discription ;

    private int mrpprice ;

    private int sellingPrice ;

    private int discount ;

    private int Quantity ;

    private String color ;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    private int numrating;

    @ManyToOne
    private Category category ;


    @ManyToOne
    private Seller seller;

    private LocalDateTime createdAt ;

    private String Sizes ;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();






}
