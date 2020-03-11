package com.example.pruebaandroid.Services;

import com.example.pruebaandroid.IServices.IProduct;
import com.example.pruebaandroid.Models.Product;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ProductService {

    private static ProductService productService;
    private IProduct iProduct;
    private AuthService authService;
    private ProductService() {
        Retrofit httpClient;
        httpClient = APIService.HttpClient();
        this.iProduct = httpClient.create(IProduct.class);
        //this.authService = AuthService.getAuthService();
    }
    public static ProductService getProductService() {
        if (productService == null) {
            productService = new ProductService();
            return  productService;
        }else {
            return ProductService.productService;
        }
    }

    public Call<ArrayList<Product>> getAll(HashMap<String,String> body){
        return this.iProduct.getALl(body);
    }
}
