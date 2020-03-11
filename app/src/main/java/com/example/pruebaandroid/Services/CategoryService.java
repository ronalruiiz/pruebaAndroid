package com.example.pruebaandroid.Services;

import com.example.pruebaandroid.IServices.ICategory;
import com.example.pruebaandroid.Models.Category;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;

public class CategoryService {
    private static CategoryService categoryService;
    private ICategory iCategory;
    private AuthService authService;
    private CategoryService() {
        Retrofit httpClient;
        httpClient = APIService.HttpClient();
        this.iCategory = httpClient.create(ICategory.class);
        //this.authService = AuthService.getAuthService();
    }

    public static CategoryService getCategoryService() {
        if (categoryService == null) {
            categoryService = new CategoryService();
            return  categoryService;
        }else {
            return CategoryService.categoryService;
        }
    }

    public Call<ArrayList<Category>> getAll(){
        return this.iCategory.getAllProducts();
    }
}
