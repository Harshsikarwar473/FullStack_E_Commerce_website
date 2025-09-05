package com.E_Commerce.Ecommerce.model;

import com.E_Commerce.Ecommerce.domin.OrderStatus;
import com.E_Commerce.Ecommerce.domin.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    private String orderId ;
    @ManyToOne
    private User user ;

    private long sellerId ;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<OrderItems> orderItems= new ArrayList<>();

    @OneToOne
    private Address shippingaddress ;
    @Embedded
    private PaymentDetails paymentDetails=new PaymentDetails();

    private double totalprice ;

    private Integer totasellingprice ;

    private Integer discount ;

    private OrderStatus orderStatus;

    private int totalitem ;

    private PaymentStatus paymentStatus = PaymentStatus.PENDING ;

    private LocalDateTime orderdate = LocalDateTime.now();

    private LocalDateTime deliverdate = orderdate.plusDays(7);


}
