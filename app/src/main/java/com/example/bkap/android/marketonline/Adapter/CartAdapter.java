package com.example.bkap.android.marketonline.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bkap.android.marketonline.Activity.DetailProductActivity;
import com.example.bkap.android.marketonline.DTO.FormatPrice;
import com.example.bkap.android.marketonline.DTO.MyCart;
import com.example.bkap.android.marketonline.DTO.Product;
import com.example.bkap.android.marketonline.R;
import com.example.bkap.android.marketonline.ROOMDATABASE.MyCartDatabase;

import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private Context mCtx;
    private List<MyCart> mListMyCart;
    private IClickItem iClickItem;

    public interface IClickItem{
        void deleteProduct(MyCart myCart);
    }

    public CartAdapter(Context mCtx, List<MyCart> mListMyCart,IClickItem iClickItem) {
        this.mCtx = mCtx;
        this.mListMyCart = mListMyCart;
        this.iClickItem = iClickItem;
    }
    public void setData(List<MyCart> list){
        this.mListMyCart = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_layout,parent,false);
        return new CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        final MyCart myCart = mListMyCart.get(position);
        if (myCart == null){
            return;
        }
        Glide.with(mCtx).load(myCart.getLinkImg()).override(100,100).centerCrop().into(holder.imgProductCart);
        holder.tvNameProductCart.setText(myCart.getNameProduct());
        holder.tvPriceProductCart.setText("Đơn Giá :" + FormatPrice.fmtCurrency((myCart.getPriceProduct() - ((myCart.getPriceProduct() * myCart.getDiscountProduct()) / 100)), new Locale("vi", "VN")));
        holder.tvExpiryProductCart.setText("HSD :" + myCart.getExpiryProduct());
        holder.tvQuantityProductCart.setText("Số lượng : " + myCart.getQuantity());
        holder.tvTotalP.setText("Thành Tiền :" + FormatPrice.fmtCurrency((myCart.getPriceProduct() * myCart.getQuantity()),new Locale("vi","VN")));

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.deleteProduct(myCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListMyCart != null){
            return mListMyCart.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProductCart;
        private TextView tvNameProductCart,tvExpiryProductCart,tvPriceProductCart,tvQuantityProductCart,tvTotalP;
        private ImageButton imgDelete;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProductCart = itemView.findViewById(R.id.imgProductCart);
            tvNameProductCart = itemView.findViewById(R.id.tvNameProductCart);
            tvExpiryProductCart = itemView.findViewById(R.id.tvExpiryProductCart);
            tvPriceProductCart = itemView.findViewById(R.id.tvPriceProductCart);
            tvQuantityProductCart = itemView.findViewById(R.id.tvQuantityProductCart);
            tvTotalP = itemView.findViewById(R.id.tvTotalP);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
