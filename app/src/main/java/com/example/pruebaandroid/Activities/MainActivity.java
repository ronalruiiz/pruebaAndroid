package com.example.pruebaandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pruebaandroid.Adapters.LvCategoryAdapter;
import com.example.pruebaandroid.Models.Category;
import com.example.pruebaandroid.Models.Purchase;
import com.example.pruebaandroid.R;
import com.example.pruebaandroid.Services.AuthService;
import com.example.pruebaandroid.Services.CategoryService;
import com.example.pruebaandroid.Services.PurchaseService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CategoryService categoryService;
    ListView lvCategories;
    AuthService authService;
    PurchaseService purchaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("CATEGORIAS");
        }
        this.authService = AuthService.getAuthService();
        this.categoryService = CategoryService.getCategoryService();
        this.purchaseService = PurchaseService.getPurchaseService();
        lvCategories = findViewById(R.id.lvCategories);


        this.categoryService.getAll().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                Log.e("Category List", "" + response.code());
                if (response.code() == 200) {
                    ArrayList<Category> arrayList = response.body();
                    LvCategoryAdapter lvCategoryAdapter = new LvCategoryAdapter(MainActivity.this, R.layout.item_list, arrayList);
                    lvCategories.setAdapter(lvCategoryAdapter);

                    lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(MainActivity.this, ProductActivity.class);

                            intent.putExtra("idCat", arrayList.get(position).idCat);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.e("Category List", t.getMessage());
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnLogout:
                if(this.authService.logout()){
                    purchaseService.removeSharedPreferences();
                    Intent intent = new Intent(this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
            case R.id.mnPurchase:
                Intent intent = new Intent(this,DetailPurchaseActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
