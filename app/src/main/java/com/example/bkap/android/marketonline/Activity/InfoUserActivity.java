package com.example.bkap.android.marketonline.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkap.android.marketonline.DTO.User;
import com.example.bkap.android.marketonline.Global.GlobalApplication;
import com.example.bkap.android.marketonline.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoUserActivity extends AppCompatActivity {
    TextView tvTitleName,tvTitlePosition,tvUserAcc,tvUserPass,tvUserPhone,tvUserName,tvUserPosition,tvUserLvRank,tvChangePass;
    Button btnLogOut;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        initView();
        setInfo();
        setToolbar();
        changePassword();
        logOut();
    }

    private void changePassword() {
        tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER);
            }
        });
    }

    private void initView() {
        tvTitleName = findViewById(R.id.tvTitleName);
        tvTitlePosition = findViewById(R.id.tvTitlePosition);
        tvUserAcc = findViewById(R.id.tvUserAcc);
        tvUserPass = findViewById(R.id.tvUserPass);
        tvUserPhone = findViewById(R.id.tvUserPhone);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserPosition = findViewById(R.id.tvUserPosition);
        tvUserLvRank = findViewById(R.id.tvUserLvRank);
        tvChangePass = findViewById(R.id.tvChangePass);
        btnLogOut = findViewById(R.id.btnLogOut);
        toolbar = findViewById(R.id.toolbar);
    }

    @SuppressLint("SetTextI18n")
    private void setInfo() {
        GlobalApplication global1 = (GlobalApplication) getApplicationContext();
        tvTitleName.setText("Hi " + global1.getgUser().getFullname() + " !");
        tvTitlePosition.setText(global1.getgUser().getPosition());
        tvUserAcc.setText(global1.getgUser().getAccount());
        tvUserPass.setText(global1.getgUser().getPassword());
        tvUserPhone.setText(global1.getgUser().getPhone());
        tvUserName.setText(global1.getgUser().getFullname());
        tvUserPosition.setText(global1.getgUser().getPosition());
        tvUserLvRank.setText(global1.getgUser().getLvrank());
    }
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Thông tin tài khoản");
    }
    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.change_password_dialog);

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

        EditText edtNewPass = dialog.findViewById(R.id.edtNewPass);
        EditText edtReNewPass = dialog.findViewById(R.id.edtReNewPass);
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

            }
        });
        dialog.show();
    }
    private void logOut(){
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApplication global = (GlobalApplication) getApplication();
                global.setgUser(null);
                Intent intent = new Intent(InfoUserActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}