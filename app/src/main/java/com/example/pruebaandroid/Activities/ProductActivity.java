package com.example.pruebaandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pruebaandroid.Adapters.LvProductAdapter;
import com.example.pruebaandroid.Models.Product;
import com.example.pruebaandroid.R;
import com.example.pruebaandroid.Services.ProductService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    ProductService productService;
    ListView lvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        lvProducts = findViewById(R.id.lvProducts);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("MENÚ");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        productService = ProductService.getProductService();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && !bundle.getString("idCat").isEmpty()) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("idCat", bundle.getString("idCat"));
            productService.getAll(hashMap).enqueue(new Callback<ArrayList<Product>>() {
                @Override
                public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                    Log.e("Product List", "" + response.code());
                    if (response.code() == 200) {
                        ArrayList<Product> productArrayList = response.body();
                        LvProductAdapter myAdapter = new LvProductAdapter(ProductActivity.this, R.layout.item_list_products, productArrayList);
                        lvProducts.setAdapter(myAdapter);
                        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String product =  new Gson().toJson(productArrayList.get(position));
                                Intent intent = new Intent(ProductActivity.this,DetailProduct.class);
                                intent.putExtra("product",product);
                                startActivity(intent);
                            }
                        });
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                }
            });
        }
    }
}
