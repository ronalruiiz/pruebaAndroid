package com.example.pruebaandroid.IServices;

import com.example.pruebaandroid.Models.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ICategory {
    @POST("/apps/wsBarRest/public/AppBar/getMenuCategorias")
    Call<ArrayList<Category>> getAllProducts();
}
