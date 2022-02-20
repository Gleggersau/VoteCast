package com.example.votecast.livestreamming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemStickerBinding;
import com.example.votecast.models.Sticker;
import com.example.votecast.z_demo.Demo_contents;

import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.StickerViewHolder> {
    List<Sticker> stickers = Demo_contents.getStickers();
    Context context;
    private OnStickerClickListner onStickerClickListner;


    public OnStickerClickListner getOns() {
        return onStickerClickListner;
    }

    public void setOnStickerClickListner(OnStickerClickListner ons) {
        this.onStickerClickListner = ons;
    }

    @NonNull
    @Override
    public StickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new StickerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sticker, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StickerViewHolder holder, int position) {

        Sticker stickerRoot = stickers.get(position);
        holder.binding.image.setImageURI(stickerRoot.image);
        holder.binding.image.setOnClickListener(v -> {
            Log.d("TAG", "onBindViewHolder: ===========" + stickerRoot.image);
            onStickerClickListner.onStickerClick(stickerRoot);
        });

    }


    @Override
    public int getItemCount() {
        return stickers.size();
    }

    public interface OnStickerClickListner {
        void onStickerClick(Sticker filterRoot);
    }

    public class StickerViewHolder extends RecyclerView.ViewHolder {
        ItemStickerBinding binding;

        public StickerViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemStickerBinding.bind(itemView);
        }
    }
}
