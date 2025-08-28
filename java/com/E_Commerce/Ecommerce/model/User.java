package com.E_Commerce.Ecommerce.model;


import com.E_Commerce.Ecommerce.domin.User_Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private String email;


    private String fullName;

    private String mobile;

    private User_Role role = User_Role.ROLE_COSTUMER;
    @OneToMany

    private Set<Address> address = new HashSet<>();
    @ManyToMany
    @JsonIgnore
    private Set<Coupon> coupon = new HashSet<>();

}
