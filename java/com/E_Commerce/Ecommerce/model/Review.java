package com.E_Commerce.Ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  long id ;
    @Column(nullable = false)
    private String reviewText ;

    @Column(nullable = false)
    private  double ratting ;

    @ElementCollection
    private List<String> productimage ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product " , nullable = false)
    private Product product ;


    @ManyToOne
    private User user ;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


}
