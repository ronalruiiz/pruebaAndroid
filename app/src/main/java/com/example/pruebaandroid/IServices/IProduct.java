package com.example.pruebaandroid.IServices;

import com.example.pruebaandroid.Models.Product;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IProduct {
    @POST("/apps/wsBarRest/public/AppBar/getListaMenu")
    Call<ArrayList<Product>> getALl(@Body HashMap<String,String> body);
}
