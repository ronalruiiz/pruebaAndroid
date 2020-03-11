package com.example.pruebaandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pruebaandroid.Models.Product;
import com.example.pruebaandroid.Models.Purchase;
import com.example.pruebaandroid.R;
import com.example.pruebaandroid.Services.PurchaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailProduct extends AppCompatActivity {

    TextView txtNameDetailProduct;
    ImageView imgViewDetailProduct;
    TextView txtDetailPrice;
    TextView txtDetailDescriptionProduct;
    Button btnDetailAddShop;
    PurchaseService purchaseService;
    EditText edTxtCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("PRODUCTOS");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        txtNameDetailProduct = findViewById(R.id.txtNameDetailProduct);
        imgViewDetailProduct = findViewById(R.id.imgViewDetailProduct);
        txtDetailPrice = findViewById(R.id.txtDetailPrice);
        edTxtCounter = findViewById(R.id.edTxtCounter);
        txtDetailDescriptionProduct = findViewById(R.id.txtDetailDescriptionProduct);
        btnDetailAddShop = findViewById(R.id.btnDetailAddShop);
        purchaseService = PurchaseService.getPurchaseService();

        if (getIntent().getExtras() != null && !getIntent().getExtras().getString("product").isEmpty()) {
            String productString = getIntent().getExtras().getString("product");
            Product product = new Gson().fromJson(productString, Product.class);
            txtNameDetailProduct.setText(product.titulo);
            txtDetailPrice.setText("$ " + product.precio);
            txtDetailDescriptionProduct.setText(product.contenido);
            Picasso.get().load(product.urlImagen).into(imgViewDetailProduct);
            btnDetailAddShop.setOnClickListener(e -> this.addShop(product));

        }

    }

    void addShop(Product product) {
        String counter = this.edTxtCounter.getText().toString();
        if (!counter.isEmpty() && Integer.parseInt(counter) > 0) {
            double totalPrice = Double.parseDouble(product.precio) * Integer.parseInt(counter);
            Purchase purchase = new Purchase(product, totalPrice, Integer.parseInt(counter));
            this.purchaseService.addPurchase(purchase);
            Intent intent = new Intent(this,DetailPurchaseActivity.class);
            startActivity(intent);
        }
    }
}
