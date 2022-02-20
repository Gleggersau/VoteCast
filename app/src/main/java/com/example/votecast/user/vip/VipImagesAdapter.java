package com.example.votecast.user.vip;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemVipSliderBinding;

public class VipImagesAdapter extends RecyclerView.Adapter<VipImagesAdapter.VipImagesViewHolder> {

    @Override
    public VipImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VipImagesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(VipImagesViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class VipImagesViewHolder extends RecyclerView.ViewHolder {
        ItemVipSliderBinding bannerBinding;

        public VipImagesViewHolder(View itemView) {
            super(itemView);
            bannerBinding = ItemVipSliderBinding.bind(itemView);
        }
    }
}
