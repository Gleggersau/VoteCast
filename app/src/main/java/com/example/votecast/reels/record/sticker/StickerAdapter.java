package com.example.votecast.reels.record.sticker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemStickerGridBinding;
import com.example.votecast.models.Sticker;

import java.util.ArrayList;
import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnStickerClickListner  onSongClickListner;
    private List<Sticker> stickers = new ArrayList<>();

    public OnStickerClickListner  getOnSongClickListner() {
        return onSongClickListner;
    }

    public void setOnSongClickListner(OnStickerClickListner  onSongClickListner) {
        this.onSongClickListner = onSongClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StickerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticker_grid, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StickerViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return stickers.size();
    }

    public void addData(List<Sticker> songs) {

        this.stickers.addAll(songs);
        notifyItemRangeInserted(this.stickers.size(), songs.size());
    }

    public interface OnStickerClickListner {
        void onStickerClick(Sticker song);
    }

    public class StickerViewHolder extends RecyclerView.ViewHolder {
        ItemStickerGridBinding binding;

        public StickerViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemStickerGridBinding.bind(itemView);

        }

        public void setData(int position) {
            Sticker song = stickers.get(position);
            binding.image.setImageURI(song.image);
            binding.getRoot().setOnClickListener(v -> onSongClickListner.onStickerClick(song));
        }
    }
}

