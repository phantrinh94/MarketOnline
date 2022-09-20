package com.example.bkap.android.marketonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bkap.android.marketonline.Activity.ProductActivity;
import com.example.bkap.android.marketonline.DTO.Category;
import com.example.bkap.android.marketonline.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private Context mCtx;
    private List<Category> mListCate;

    public CategoryAdapter(Context mCtx, List<Category> mListCate) {
        this.mCtx = mCtx;
        this.mListCate = mListCate;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_layout,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category cate = mListCate.get(position);
        if (cate == null){
            return;
        }
        Glide.with(mCtx).load(cate.getImglink()).centerCrop().into(holder.imgCate);
        holder.tvNameCate.setText(cate.getCateName());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idCate = cate.getId();
                Intent i = new Intent(mCtx, ProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idCate",idCate);
                i.putExtras(bundle);
                mCtx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListCate != null){
            return mListCate.size();
        }
        return 0;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCate;
        private TextView tvNameCate;
        private CardView itemLayout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCate = itemView.findViewById(R.id.imgCategory);
            tvNameCate = itemView.findViewById(R.id.tvNameCategory);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}
