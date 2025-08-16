package com.E_Commerce.Ecommerce.model;

import lombok.Data;

import java.util.List;

@Data
public class Home {

    private List<HomeCatogory> grid ;
    private List<HomeCatogory> shopByCategory ;
    private List<HomeCatogory> electricCategory ;
    private List<HomeCatogory> dealCategpries ;
    private List<Deal> deals ;
}
