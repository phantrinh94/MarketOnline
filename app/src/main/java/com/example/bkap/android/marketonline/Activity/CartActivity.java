package com.example.bkap.android.marketonline.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkap.android.marketonline.Adapter.CartAdapter;
import com.example.bkap.android.marketonline.DTO.FormatPrice;
import com.example.bkap.android.marketonline.DTO.Messages;
import com.example.bkap.android.marketonline.DTO.MyCart;
import com.example.bkap.android.marketonline.DTO.MyInvoice;
import com.example.bkap.android.marketonline.DTO.Product;
import com.example.bkap.android.marketonline.DTO.User;
import com.example.bkap.android.marketonline.Global.GlobalApplication;
import com.example.bkap.android.marketonline.R;
import com.example.bkap.android.marketonline.ROOMDATABASE.MyCartDatabase;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private List<MyCart> mListMyCart;
    private RecyclerView recyclerViewCart;
    private Toolbar toolbar;
    private TextView tvTotalMoney;
    private Button btnThanhToan;
    private float totalMoney;
    private GlobalApplication global;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        setToolbar();
        setAdapterCart();
        onClickButton();
    }

    private void onClickButton() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApplication global1 = (GlobalApplication) getApplicationContext();
                if (global1.getgUser() == null){
                    Toast.makeText(CartActivity.this, "Vui lòng đăng nhập để thanh toán", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0; i < mListMyCart.size(); i++) {
                        int id = mListMyCart.get(i).getIdP();
                        int quantity = mListMyCart.get(i).getQuantity();
                        float price = mListMyCart.get(i).getPriceProduct();
                        GlobalApplication global = (GlobalApplication) getApplication();
                        global.updateCart(id,quantity,price);
                        Toast.makeText(CartActivity.this, "Thanh Toán Thành Công", Toast.LENGTH_SHORT).show();
                    }
                    openDialog(Gravity.CENTER);
                    MyCartDatabase.getInstance(CartActivity.this).myCartDAO().deleteAll();
                }
            }
        });
    }



    private void initView() {
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        toolbar = findViewById(R.id.toolbar);
        tvTotalMoney = findViewById(R.id.tvTotalMoney);
        btnThanhToan = findViewById(R.id.btn_ThanhToan);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.my_cart);
    }
    private void loadDate(){
        mListMyCart = MyCartDatabase.getInstance(this).myCartDAO().getListMyCart();
        cartAdapter.setData(mListMyCart);
        totalMoney = 0;
        for (MyCart myC:mListMyCart) {
            totalMoney= totalMoney +(myC.getPriceProduct()*myC.getQuantity());
        }
        tvTotalMoney.setText(FormatPrice.fmtCurrency(Double.parseDouble((totalMoney + "")),new Locale("vi","VN")));
    }

    private void setAdapterCart(){
        cartAdapter = new CartAdapter(CartActivity.this, mListMyCart, new CartAdapter.IClickItem() {
            @Override
            public void deleteProduct(MyCart myCart) {
                clickDelete(myCart);
            }
        });
        loadDate();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CartActivity.this,1);
        recyclerViewCart.setLayoutManager(gridLayoutManager);
        recyclerViewCart.setAdapter(cartAdapter);
    }



    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.set_invoice_dialog);

        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }


        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Button btnOke = dialog.findViewById(R.id.btn_oke);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtAddressInvoice = dialog.findViewById(R.id.edtAddressInvoice);
                String addressInvoice = edtAddressInvoice.getText().toString();
                EditText edtPhoneInvoice = dialog.findViewById(R.id.edtPhoneInvoice);
                String phoneInvoice = edtPhoneInvoice.getText().toString();
                global = (GlobalApplication) getApplication();
                global.setCustomInfo(addressInvoice,phoneInvoice,totalMoney);
                Gson gson = new Gson();
                String jsonUser = gson.toJson(global.getgCart());

                Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Messages msg = gson.fromJson(response.toString(),Messages.class);
                        Toast.makeText(CartActivity.this, msg.getMsg(), Toast.LENGTH_SHORT).show();
                        edtAddressInvoice.setText("");
                        edtPhoneInvoice.setText("");
                        dialog.dismiss();
                        Intent i = new Intent(CartActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CartActivity.this, "Lỗi thêm dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                };

                String api = "http://192.168.67.2:8080/AristinoShopBK/rest/cart/buynow";

                try {
                    JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST,api,new JSONObject(jsonUser),listener,errorListener);
                    RequestQueue requestQueue = Volley.newRequestQueue(CartActivity.this);
                    requestQueue.add(jor);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.show();
    }

    private void clickDelete(MyCart myCart) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete Product")
                .setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyCartDatabase.getInstance(CartActivity.this).myCartDAO().deleteProduct(myCart);
                        Toast.makeText(CartActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        loadDate();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }


}