package com.example.votecast.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemBannerBinding;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private Context context;

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(BannerAdapter.BannerViewHolder holder, int position) {
        if (position % 2==0) {
            holder.bannerBinding.imageview.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.banner));
        } else {
            holder.bannerBinding.imageview.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.banner2));
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        ItemBannerBinding bannerBinding;

        public BannerViewHolder(View itemView) {
            super(itemView);
            bannerBinding = ItemBannerBinding.bind(itemView);
        }
    }
}
