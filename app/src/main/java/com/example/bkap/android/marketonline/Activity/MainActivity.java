package com.example.bkap.android.marketonline.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkap.android.marketonline.Adapter.CategoryAdapter;
import com.example.bkap.android.marketonline.DTO.Category;
import com.example.bkap.android.marketonline.Global.GlobalApplication;
import com.example.bkap.android.marketonline.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView txtLoading;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setToolbar();
        this.txtLoading.setVisibility(View.VISIBLE);
        getJsonData();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerViewCategory);
        toolbar = findViewById(R.id.toolbar);
        txtLoading = findViewById(R.id.txt_loading);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_home_24);
        toolbar.setTitle(R.string.home);
    }

    public void getJsonData(){
        String url = "http://192.168.67.2:8080/AristinoShopBK/rest/category/all";
        //Xử lý dữ liệu trả về
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txtLoading.setVisibility(View.GONE);
                Gson gson = new Gson();
                TypeToken<List<Category>> token = new TypeToken<List<Category>>() {
                };
                List<Category> lst = gson.fromJson(response, token.getType());
                CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, lst);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,1);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(categoryAdapter);
            }
        };
        //Xử lý khi truy cập lỗi
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error when get list category:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                txtLoading.setVisibility(View.GONE);
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_cart:
                Intent i = new Intent(MainActivity.this,CartActivity.class);
                startActivity(i);
                break;
            case R.id.menu_user:
                GlobalApplication global = (GlobalApplication) getApplicationContext();
                if (global.getgUser() == null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent2 = new Intent(MainActivity.this, InfoUserActivity.class);
                    startActivity(intent2);
                }

                break;
            case R.id.menu_manage:
                GlobalApplication global1 = (GlobalApplication) getApplicationContext();
                if (global1.getgUser() == null){
                    Toast.makeText(this, "Bạn phải đăng nhập để thực hiện thao tác này", Toast.LENGTH_SHORT).show();
                }else {
                    String rank = global1.getgUser().getLvrank();
                    if (rank.equals("1")){
                        Intent intent1 = new Intent(MainActivity.this, AdminManagerActivity.class);
                        startActivity(intent1);
                        finish();
                    }else {
                        item.setVisible(false);
                    }
                }
                break;

            case R.id.menu_invoice:
                GlobalApplication global3 = (GlobalApplication) getApplicationContext();
                if (global3.getgUser() == null){
                    Toast.makeText(this, "Bạn cần đăng nhập trước!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this,ShowInvoiceActivity.class);
                    startActivity(intent);
                    break;
                }

        }

        return super.onOptionsItemSelected(item);
    }

}