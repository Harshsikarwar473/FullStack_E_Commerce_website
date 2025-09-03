package com.E_Commerce.Ecommerce.model;


import com.E_Commerce.Ecommerce.domin.AccountStatus;
import com.E_Commerce.Ecommerce.domin.User_Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  long id ;

    private String name ;

    private String mobile ;


    @Column(nullable = false)
    private String email ;

    private String password ;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();


    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne
    private Address pickupAddress = new Address();

    private String GSTIN ;

    private User_Role role = User_Role.ROLE_SELLER ;

    private boolean isEmailverified = false;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION ;

}
