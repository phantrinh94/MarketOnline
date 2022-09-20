package com.example.bkap.android.marketonline.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkap.android.marketonline.Adapter.ProductAdapter;
import com.example.bkap.android.marketonline.DTO.Product;
import com.example.bkap.android.marketonline.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private int idCate;
    private RecyclerView recyclerViewProduct;
    private Toolbar toolbar;
    private SearchView searchView;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initView();
        setToolbar();
        getData();
        getProduct(idCate);

    }

    private void initView() {
        recyclerViewProduct = findViewById(R.id.recyclerViewProduct);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.product);
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        idCate = (int) bundle.get("idCate");
    }

    public void getProduct(int idCate) {
        String url = "http://192.168.67.2:8080/AristinoShopBK/rest/product/bycategoryid/" + idCate;
        //Xử lý dữ liệu trả về
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TypeToken<List<Product>> token = new TypeToken<List<Product>>() {
                };
                List<Product> lst = gson.fromJson(response, token.getType());
                productAdapter = new ProductAdapter(ProductActivity.this, lst);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductActivity.this, 2);
                recyclerViewProduct.setLayoutManager(gridLayoutManager);
                recyclerViewProduct.setAdapter(productAdapter);

            }
        };
        //Xử lý khi truy cập lỗi
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductActivity.this, "Lấy data thất bại", Toast.LENGTH_SHORT).show();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(ProductActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}