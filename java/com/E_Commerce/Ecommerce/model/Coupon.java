package com.E_Commerce.Ecommerce.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    private String code ;


    private double discount ;

    private LocalDate validityStartDate ;

    private LocalDate validityEndDate ;

    private double minimumOrdervalue ;

    private boolean isActive = true ;
    @ManyToMany
    private Set<User> UsedByUsers = new HashSet<>() ;





}
