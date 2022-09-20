package com.example.bkap.android.marketonline.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bkap.android.marketonline.DTO.FormatPrice;
import com.example.bkap.android.marketonline.DTO.MyInvoice;
import com.example.bkap.android.marketonline.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterInvoice extends ArrayAdapter<MyInvoice> {
    private Context mCtx;
    private int mLayout;
    private List<MyInvoice> mList;
    public AdapterInvoice(@NonNull Context context, int resource, @NonNull List<MyInvoice> objects) {
        super(context, resource, objects);
        this.mCtx = context;
        this.mLayout = resource;
        this.mList = objects;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null){
            item = LayoutInflater.from(mCtx).inflate(mLayout,parent,false);
        }
        TextView tvID = item.findViewById(R.id.tvID);
        TextView tvAddress = item.findViewById(R.id.tvAddress);
        TextView tvPhone = item.findViewById(R.id.tvPhone);
        TextView tvAmount = item.findViewById(R.id.tvTotalMoney1);
        TextView tvTime = item.findViewById(R.id.tvTime);

        MyInvoice invoice = mList.get(position);

        tvID.setText(invoice.getId() + "");
        tvAddress.setText(invoice.getAddress());
        tvPhone.setText(invoice.getPhone());
        String a = FormatPrice.fmtCurrency(invoice.getTotalMoney(),new Locale("vi","VN"));
        Log.e("TP",a);
        tvAmount.setText(a);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
        tvTime.setText(formatter.format(invoice.getCreateDate()));


        return item;
    }
}
