package com.E_Commerce.Ecommerce.model;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  long id ;

    private String name ;


    @Column(unique = true)
    private String catagoryId ;

    @ManyToOne
    private Category parent ;


    private Integer level ;


}
