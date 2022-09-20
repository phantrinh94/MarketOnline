package com.example.bkap.android.marketonline.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkap.android.marketonline.Adapter.AdapterInvoiceDetail;
import com.example.bkap.android.marketonline.DTO.InvoiceDetail;
import com.example.bkap.android.marketonline.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class InvoiceDetailActivity extends AppCompatActivity {
    private ListView listView;
    private int mIdI;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_detail);
        initView();
        getDataIdI();
        jsonDataInvoiceDetail(mIdI);
        setToolbar();
    }

    private void initView() {
        listView = findViewById(R.id.lstViewInvoiceDetail);
        toolbar = findViewById(R.id.toolbar);
    }

    public void getDataIdI(){
        mIdI = (int) getIntent().getExtras().get("id");
    }

    public void jsonDataInvoiceDetail(int idI) {
        String url = "http://192.168.67.2:8080/AristinoShopBK/rest/cart/list-invoice-detail/" + idI;
        //Xử lý dữ liệu trả về
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TypeToken<List<InvoiceDetail>> token = new TypeToken<List<InvoiceDetail>>() {
                };
                List<InvoiceDetail> lst = gson.fromJson(response, token.getType());
                AdapterInvoiceDetail adapter = new AdapterInvoiceDetail(InvoiceDetailActivity.this, R.layout.item_invoice_detail, lst);
                listView.setAdapter(adapter);
            }
        };
        //Xử lý khi truy cập lỗi
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InvoiceDetailActivity.this, "Lấy data thất bại", Toast.LENGTH_SHORT).show();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(InvoiceDetailActivity.this);
        requestQueue.add(stringRequest);

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_home_24);
        toolbar.setTitle(R.string.invoice_detail);
    }
}