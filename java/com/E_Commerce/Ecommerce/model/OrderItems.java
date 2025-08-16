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
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    @JsonIgnore
    @ManyToOne
    private Order order ;

    @ManyToOne
    private Product product ;

    private String size ;

    private int quantity ;

    private Integer mrpPrice ;

    private Integer sellingPrice ;

    private Long userId ;


}
