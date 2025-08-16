package com.E_Commerce.Ecommerce.model;


import com.E_Commerce.Ecommerce.domin.PaymentStatus;
import lombok.Data;


@Data
public class PaymentDetails {
    private String paymentId ;
    private String rozorpayPaymentLinkid ;
    private String rozorpayPaymentLinkrefrenceid ;
    private String rozorpayPaymentLinkStatus ;
    private String rozorpayPaymentIdZWSP;
    private PaymentStatus status ;

}
