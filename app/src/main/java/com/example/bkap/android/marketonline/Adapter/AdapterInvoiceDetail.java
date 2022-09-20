package com.example.bkap.android.marketonline.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bkap.android.marketonline.DTO.FormatPrice;
import com.example.bkap.android.marketonline.DTO.InvoiceDetail;
import com.example.bkap.android.marketonline.R;

import java.util.List;
import java.util.Locale;

public class AdapterInvoiceDetail extends ArrayAdapter<InvoiceDetail> {
    private Context mContext;
    private int mLayout;
    private List<InvoiceDetail> mList;
    public AdapterInvoiceDetail(@NonNull Context context, int resource, @NonNull List<InvoiceDetail> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mLayout = resource;
        this.mList = objects;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(mContext).inflate(mLayout,parent,false);
        }
        TextView tvIdP = itemView.findViewById(R.id.tvIdP);
        TextView tvNameP = itemView.findViewById(R.id.tvNameP);
        TextView tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        TextView tvGiaHoaDon = itemView.findViewById(R.id.tvGiaHoaDon);
        TextView tvTongGia = itemView.findViewById(R.id.tvTongGia);
        TextView tvGhiChu = itemView.findViewById(R.id.tvGhiChu);
        InvoiceDetail invoiceDetail = mList.get(position);

        tvIdP.setText(invoiceDetail.getIdP() + "");
        tvNameP.setText(invoiceDetail.getProductName());
        tvSoLuong.setText(invoiceDetail.getQuantity() + "");
        tvGiaHoaDon.setText(FormatPrice.fmtCurrency(invoiceDetail.getPrice(), new Locale("vi", "VN")));
        int tongGia = (int) (invoiceDetail.getQuantity()*invoiceDetail.getPrice());
        tvTongGia.setText(FormatPrice.fmtCurrency(tongGia, new Locale("vi", "VN")));
        tvGhiChu.setText(invoiceDetail.getNote());

        return itemView;
    }
}
