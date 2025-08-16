package com.E_Commerce.Ecommerce.model;


import com.E_Commerce.Ecommerce.domin.HomeCatogorysection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HomeCatogory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  long id ;

    private String name ;
   private  String image ;
   private String catogoryId ;
   private HomeCatogorysection section ;



}
