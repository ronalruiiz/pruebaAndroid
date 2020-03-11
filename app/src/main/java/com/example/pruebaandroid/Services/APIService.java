package com.example.pruebaandroid.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private static Retrofit retrofit;
    private static String BASE_URL = "http://viamatica.me";

    private APIService(){
    }

    public static Retrofit HttpClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            return retrofit;
        }
        return retrofit;
    }
}
