package com.example.pruebaandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.pruebaandroid.Adapters.LvPurchaseAdapter;
import com.example.pruebaandroid.Models.Purchase;
import com.example.pruebaandroid.R;
import com.example.pruebaandroid.Services.PurchaseService;

import java.util.ArrayList;

public class DetailPurchaseActivity extends AppCompatActivity {
    ListView lvPurchase;
    PurchaseService purchaseService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_purchase);
        lvPurchase = findViewById(R.id.lvPurchase);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("CARRITO");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        purchaseService = PurchaseService.getPurchaseService();

        ArrayList<Purchase> purchaseArrayList = this.purchaseService.getList();
        LvPurchaseAdapter myAdapter = new LvPurchaseAdapter(DetailPurchaseActivity.this, R.layout.item_list_purchase, purchaseArrayList);
        lvPurchase.setAdapter(myAdapter);
    }
}
