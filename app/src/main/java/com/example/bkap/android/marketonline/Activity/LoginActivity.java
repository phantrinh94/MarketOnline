package com.example.bkap.android.marketonline.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkap.android.marketonline.DTO.Messages;
import com.example.bkap.android.marketonline.DTO.User;
import com.example.bkap.android.marketonline.Global.GlobalApplication;
import com.example.bkap.android.marketonline.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    TextView tvLogin,tvRegister,tvPosition,tvLvRank,tvImgRank;
    LinearLayout lnLogin,lnRegister;
    EditText edtRegisterAcc,edtRegisterPass,edtRegisterPhone,edtRegisterName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        onClick();
    }

    private void onClick() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                tvLogin.setTextColor(Color.parseColor("#F15050"));
                tvRegister.setTextColor(Color.parseColor("#D5CAC4"));
                lnLogin.setVisibility(View.VISIBLE);
                lnRegister.setVisibility(View.GONE);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                tvRegister.setTextColor(Color.parseColor("#F15050"));
                tvLogin.setTextColor(Color.parseColor("#D5CAC4"));
                lnLogin.setVisibility(View.GONE);
                lnRegister.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView() {
        tvLogin = findViewById(R.id.tvLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvLvRank = findViewById(R.id.tvRegisterLvRank);
        tvImgRank = findViewById(R.id.tvRegisterImgRank);
        lnLogin = findViewById(R.id.lnLogin);
        lnRegister = findViewById(R.id.lnRegister);
        tvPosition = findViewById(R.id.tvRegisterPosition);
        edtRegisterAcc = findViewById(R.id.edtRegisterAcc);
        edtRegisterPass = findViewById(R.id.edtRegisterPass);
        edtRegisterPhone = findViewById(R.id.edtRegisterPhone);
        edtRegisterName = findViewById(R.id.edtRegisterName);
    }

    public void clickLogin(View view) {
        String account = ((EditText) findViewById(R.id.edtLoginAcc)).getText().toString();
        String password = ((EditText) findViewById(R.id.edtLoginPass)).getText().toString();
        User uParam = new User();
        uParam.setAccount(account);
        uParam.setPassword(password);
        String strUParam = new Gson().toJson(uParam);
        JSONObject jsoObj = null;
        try {
            jsoObj = new JSONObject(strUParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Xử lý dữ liệu trả về
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                if (response.toString().isEmpty()) {
                    openDialog();
                } else {
                    User u = gson.fromJson(response.toString(), User.class);
                    GlobalApplication gApp = (GlobalApplication) getApplicationContext();
                    gApp.setgUser(u);

                    // Chuyển màn hình
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        // Xử lý khi truy cập lỗi
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                openDialog();
            }
        };
        String url = "http://192.168.67.2:8080/AristinoShopBK/rest/user/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsoObj, listener, errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
    private void openDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        //Set tiêu đề
        dialog.setTitle("Thông báo!");
        dialog.setMessage("Đăng nhập thất bại.Vui lòng kiểm tra lại");
        dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public void clickRegister(View view) {
        String acc = edtRegisterAcc.getText().toString();
        String pass = edtRegisterPass.getText().toString();
        String phone = edtRegisterPhone.getText().toString();
        String name = edtRegisterName.getText().toString();
        String position = tvPosition.getText().toString();
        String lvrank = tvLvRank.getText().toString();
        String linkimglv = tvImgRank.getText().toString();

        User u = new User();
        u.setAccount(acc);
        u.setPassword(pass);
        u.setPhone(phone);
        u.setFullname(name);
        u.setPosition(position);
        u.setLvrank(lvrank);
        u.setLinkimglv(linkimglv);

        Gson gson = new Gson();
        String jsonUser = gson.toJson(u);

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Messages msg = gson.fromJson(response.toString(),Messages.class);
                Toast.makeText(LoginActivity.this, msg.getMsg(), Toast.LENGTH_SHORT).show();
                edtRegisterAcc.setText("");
                edtRegisterPass.setText("");
                edtRegisterPhone.setText("");
                edtRegisterName.setText("");
                tvLogin.setTextColor(Color.parseColor("#FF6D00"));
                tvRegister.setTextColor(Color.parseColor("#787572"));
                lnLogin.setVisibility(View.VISIBLE);
                lnRegister.setVisibility(View.GONE);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Lỗi thêm dữ liệu", Toast.LENGTH_SHORT).show();
            }
        };

        String api = "http://192.168.67.2:8080/AristinoShopBK/rest/user/insert";
        try {
            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST,api,new JSONObject(jsonUser),listener,errorListener);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}