package com.example.bkap.android.marketonline.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.bkap.android.marketonline.DTO.FormatPrice;
import com.example.bkap.android.marketonline.DTO.Messages;
import com.example.bkap.android.marketonline.DTO.MyCart;
import com.example.bkap.android.marketonline.DTO.Product;
import com.example.bkap.android.marketonline.Global.GlobalApplication;
import com.example.bkap.android.marketonline.R;
import com.example.bkap.android.marketonline.ROOMDATABASE.MyCartDatabase;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class DetailProductActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imgDetailProduct;
    private TextView tvDiscountDetailProduct, tvNameDetailProduct, tvPriceFirstDetailProduct, tvPricePosteriorDetailProduct, tvExpiryDetailProduct, tvContentDetailProduct;
    private EditText edtAmountProduct;
    private Button btnAddCartDetail;
    private Product p;
    private int idProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        initView();
        getData();
        setToolbar();
        getDataDetailsProduct(idProduct);
        addMyCart();
        getDataBase();
    }

    private void getDataBase() {
        int quantity = MyCartDatabase.getInstance(this).myCartDAO().getNumber(idProduct);
        edtAmountProduct.setText(quantity + "");
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        imgDetailProduct = findViewById(R.id.imgDetailProduct);
        tvDiscountDetailProduct = findViewById(R.id.tvDiscountDetailProduct);
        tvNameDetailProduct = findViewById(R.id.tvNameDetailProduct);
        tvPriceFirstDetailProduct = findViewById(R.id.tvPriceFirstDetailProduct);
        tvPricePosteriorDetailProduct = findViewById(R.id.tvPricePosteriorDetailProduct);
        tvExpiryDetailProduct = findViewById(R.id.tvExpiryDetailProduct);
        tvContentDetailProduct = findViewById(R.id.tvContentDetailProduct);
        edtAmountProduct = findViewById(R.id.edtAmountProduct);
        btnAddCartDetail = findViewById(R.id.btn_addCart);
    }


    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        idProduct = (int) bundle.get("idCate");
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.detail_product);
    }

    private void getDataDetailsProduct(int idProduct) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                p = gson.fromJson(response, Product.class);

                Glide.with(DetailProductActivity.this).load(p.getImglink()).centerCrop().into(imgDetailProduct);
                tvDiscountDetailProduct.setText("Sale " + p.getDiscount() + "%");
                tvNameDetailProduct.setText(p.getProductName());
                tvPriceFirstDetailProduct.setText(FormatPrice.fmtCurrency(p.getPriceProduct(), new Locale("vi", "VN")));
                tvPricePosteriorDetailProduct.setText(FormatPrice.fmtCurrency((p.getPriceProduct() - ((p.getPriceProduct() * p.getDiscount()) / 100)), new Locale("vi", "VN")));
                tvExpiryDetailProduct.setText(p.getExpiry());
                tvContentDetailProduct.setText(p.getContent());

            }
        };
        //Xử lý khi truy cập lỗi
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailProductActivity.this, "Lấy data thất bại", Toast.LENGTH_SHORT).show();
            }
        };
        String url = "http://192.168.67.2:8080/AristinoShopBK/rest/product/byproductid/" + idProduct;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(DetailProductActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
    private void addMyCart() {
        btnAddCartDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCart myCart = MyCartDatabase.getInstance(DetailProductActivity.this).myCartDAO().getMyCartByID(idProduct);
                int quantity = Integer.parseInt(edtAmountProduct.getText().toString().trim());
                if (myCart != null){
                    myCart.setQuantity(quantity);
                    MyCartDatabase.getInstance(DetailProductActivity.this).myCartDAO().insertMyCart(myCart);
                    Toast.makeText(DetailProductActivity.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }else{
                    int idP = p.getId();
                    String imgLink = p.getImglink();
                    String nameP = p.getProductName();
                    String expiryP = p.getExpiry();
                    float priceP = p.getPriceProduct();
                    String contentP = p.getContent();
                    myCart = new MyCart(idP,nameP,expiryP,priceP,contentP,imgLink,quantity);
                    MyCartDatabase.getInstance(DetailProductActivity.this).myCartDAO().insertMyCart(myCart);
                    Toast.makeText(DetailProductActivity.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}