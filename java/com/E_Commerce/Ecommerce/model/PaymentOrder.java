package com.E_Commerce.Ecommerce.model;


import com.E_Commerce.Ecommerce.domin.PaymentMethod;
import com.E_Commerce.Ecommerce.domin.PaymentOrderStatus;
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
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  long id ;

    private long amount ;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod;

    private String paymentlinkId ;
    @ManyToOne
    private User user ;
    @OneToMany
    private Set<Order> orders = new HashSet<>();

}
