package com.example.votecast.reels.record.songpicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemSongBinding;
import com.example.votecast.models.Song;

import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnSongClickListner onSongClickListner;
    private List<Song> songs = new ArrayList<>();

    public OnSongClickListner getOnSongClickListner() {
        return onSongClickListner;
    }

    public void setOnSongClickListner(OnSongClickListner onSongClickListner) {
        this.onSongClickListner = onSongClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SongsViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public void addData(List<Song> songs) {

        this.songs.addAll(songs);
        notifyItemRangeInserted(this.songs.size(), songs.size());
    }

    public interface OnSongClickListner {
        void onSongClick(Song song);
    }

    public class SongsViewHolder extends RecyclerView.ViewHolder {
        ItemSongBinding binding;

        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSongBinding.bind(itemView);
            Glide.with(itemView).load(R.drawable.song).circleCrop().into(binding.imgSong);
        }

        public void setData(int position) {
            Song song = songs.get(position);
            Glide.with(binding.getRoot()).load(song.albumImage).circleCrop().into(binding.imgSong);
            binding.title.setText(song.title);
            binding.info.setText(song.artist);
            binding.getRoot().setOnClickListener(v -> onSongClickListner.onSongClick(song));
        }
    }
}

