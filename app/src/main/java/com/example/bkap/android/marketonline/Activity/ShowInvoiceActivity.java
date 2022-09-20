package com.example.bkap.android.marketonline.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkap.android.marketonline.Adapter.AdapterInvoice;
import com.example.bkap.android.marketonline.DTO.MyInvoice;
import com.example.bkap.android.marketonline.Global.GlobalApplication;
import com.example.bkap.android.marketonline.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.List;

public class ShowInvoiceActivity extends AppCompatActivity {
    private ListView mListView;
    private List<MyInvoice> lst;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_invoice);
        
        initView();
        jsonDataCate();
        onClickInvoice();
        setToolbar();
    }

    private void initView() {
        mListView = findViewById(R.id.listViewInvoice);
        toolbar = findViewById(R.id.toolbar);
    }

    public void jsonDataCate() {
        GlobalApplication global = (GlobalApplication) getApplicationContext();
        int idI = global.getgUser().getId();
        String url = "http://192.168.67.2:8080/AristinoShopBK/rest/cart/list-invoice/" + idI;
        //Xử lý dữ liệu trả về
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ShowInvoiceActivity.this, "Connect Service", Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                TypeToken<List<MyInvoice>> token = new TypeToken<List<MyInvoice>>() {
                };
                lst = gson.fromJson(response, token.getType());
                Collections.reverse(lst);
                AdapterInvoice adapter = new AdapterInvoice(ShowInvoiceActivity.this, R.layout.item_invoice_layout, lst);
                mListView.setAdapter(adapter);

            }
        };
        //Xử lý khi truy cập lỗi
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowInvoiceActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        VolleyLog.DEBUG = true;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(ShowInvoiceActivity.this);
        requestQueue.add(stringRequest);
    }
    private void onClickInvoice(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idI = lst.get(i).getId();
                Intent intent = new Intent(ShowInvoiceActivity.this,InvoiceDetailActivity.class);
                intent.putExtra("id",idI);
                startActivity(intent);
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_home_24);
        toolbar.setTitle(R.string.invoice);
    }
}