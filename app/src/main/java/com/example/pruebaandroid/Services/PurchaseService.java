package com.example.pruebaandroid.Services;

import android.content.SharedPreferences;

import com.example.pruebaandroid.Models.Product;
import com.example.pruebaandroid.Models.Purchase;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PurchaseService {
    private static PurchaseService purchaseService;
    private AuthService authService;
    private ArrayList<Purchase> arrayList;
    private SharedPreferences sharedPreferences;
    private PurchaseService(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.arrayList = new ArrayList<>();
    }

    public static PurchaseService getPurchaseService(SharedPreferences preferences) {
        if (purchaseService == null) {
            purchaseService = new PurchaseService(preferences);
            return purchaseService;
        }else {
            return PurchaseService.purchaseService;
        }
    }
    public static PurchaseService getPurchaseService() {
        return getPurchaseService(PurchaseService.purchaseService.sharedPreferences);
    }
    public boolean addPurchase(Purchase purchase){
        this.arrayList.add(purchase);
        String ArrayList = new Gson().toJson(this.arrayList);
        this.sharedPreferences.edit().putString("purchase",ArrayList).apply();
        return true;
    }

    public void removeSharedPreferences() {
        this.arrayList = new ArrayList<>();
        this.sharedPreferences.edit().clear().apply();
    }
    public ArrayList<Purchase> getList(){
        return this.arrayList;
    }
}
