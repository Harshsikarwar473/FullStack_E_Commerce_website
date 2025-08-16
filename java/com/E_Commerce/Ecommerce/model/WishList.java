package com.E_Commerce.Ecommerce.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  long id ;

    @OneToOne
    private User user ;
    @ManyToMany
    private Set<Product> product= new HashSet<>();
}
