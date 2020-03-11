package com.example.pruebaandroid.App;

import android.app.Application;

import com.example.pruebaandroid.Services.AuthService;
import com.example.pruebaandroid.Services.PurchaseService;

public class MyApp extends Application {
    PurchaseService purchaseService;
    AuthService authService;
    @Override
    public void onCreate() {
        super.onCreate();
        this.purchaseService = PurchaseService.getPurchaseService(getSharedPreferences("purchase",MODE_PRIVATE));
        this.authService = AuthService.getAuthService(getSharedPreferences("auth",MODE_PRIVATE));
    }
}
