package com.example.bkap.android.marketonline.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bkap.android.marketonline.Activity.DetailProductActivity;
import com.example.bkap.android.marketonline.Activity.ProductActivity;
import com.example.bkap.android.marketonline.DTO.Category;
import com.example.bkap.android.marketonline.DTO.FormatPrice;
import com.example.bkap.android.marketonline.DTO.Product;
import com.example.bkap.android.marketonline.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {
    private Context mCtx;
    private List<Product> mListProduct;
    private List<Product> mListProductOld;

    public ProductAdapter(Context mCtx, List<Product> mListProduct) {
        this.mCtx = mCtx;
        this.mListProduct = mListProduct;
        this.mListProductOld = mListProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_layout,parent,false);
        return new ProductViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = mListProduct.get(position);
        if (p == null){
            return;
        }
        Glide.with(mCtx).load(p.getImglink()).centerCrop().into(holder.imgProduct);
        holder.tvNameProduct.setText(p.getProductName());
        holder.tvPriceDiscount.setText(FormatPrice.fmtCurrency((p.getPriceProduct() - ((p.getPriceProduct() * p.getDiscount()) / 100)), new Locale("vi", "VN")));
        holder.tvPriceProduct.setText(FormatPrice.fmtCurrency(p.getPriceProduct(),new Locale("vi","VN")));
        if (p.getDiscount() == 0){
            holder.tvDiscountProduct.setVisibility(View.INVISIBLE);
        }else {
            holder.tvDiscountProduct.setText("Sale " + p.getDiscount()+ "%");
        }
        holder.tvExpiryProduct.setText(p.getExpiry());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idProduct = p.getId();
                Intent i = new Intent(mCtx, DetailProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idCate",idProduct);
                i.putExtras(bundle);
                mCtx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListProduct != null){
            return mListProduct.size();
        }
        return 0;
    }



    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct;
        private TextView tvNameProduct,tvPriceProduct,tvDiscountProduct,tvExpiryProduct,tvPriceDiscount;
        private CardView itemLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);
            tvDiscountProduct = itemView.findViewById(R.id.tvDiscountProduct);
            tvExpiryProduct = itemView.findViewById(R.id.tvExpiryProduct);
            tvPriceDiscount = itemView.findViewById(R.id.tvPriceDiscount);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    mListProduct = mListProductOld;
                }else {
                    List<Product> list = new ArrayList<>();
                    for (Product p:mListProductOld) {
                        if (p.getProductName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(p);
                        }
                    }
                    mListProduct = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListProduct;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListProduct = (List<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
