package com.example.pruebaandroid.Models;

import com.google.gson.Gson;

public class Purchase {
    public Product product;
    public double priceTotal;
    public int count;

    public Purchase(Product product,double priceTotal,int count){
        this.product = product;
        this.priceTotal = priceTotal;
        this.count = count;
    }
    public Purchase(){
    }

    public static Product fromJson(String string){
       return new Gson().fromJson(string,Product.class);
    }
}
