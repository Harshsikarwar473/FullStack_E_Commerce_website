package com.E_Commerce.Ecommerce.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SellerReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  long id ;
    @OneToOne
    private Seller seller ;

    private long totalEarning= 0L ;

    private long totalsales = 0L ;

    private long totalrefunds = 0L ;

    private long totaltax = 0L ;

    private long netearning = 0L  ;

    private Integer totalorders = 0 ;

    private Integer cancelorders = 0 ;

    private Integer totaltransaction = 0 ;



}
