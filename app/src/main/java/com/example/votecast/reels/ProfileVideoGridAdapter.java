package com.example.votecast.reels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemVidProfileListBinding;
import com.example.votecast.models.Reels;

import java.util.ArrayList;
import java.util.List;

public class ProfileVideoGridAdapter extends RecyclerView.Adapter<ProfileVideoGridAdapter.FeedViewHolder> {

    OnVideoGridClickListner onVideoGridClickListner;
    private List<Reels> reels = new ArrayList<>();

    public OnVideoGridClickListner getOnVideoGridClickListner() {
        return onVideoGridClickListner;
    }

    public void setOnVideoGridClickListner(OnVideoGridClickListner onVideoGridClickListner) {
        this.onVideoGridClickListner = onVideoGridClickListner;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vid_profile_list, parent, false));

    }

    @Override
    public void onBindViewHolder(ProfileVideoGridAdapter.FeedViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return reels.size();
    }

    public void addData(List<Reels> reels) {

        this.reels.addAll(reels);
        notifyItemRangeInserted(this.reels.size(), reels.size());
    }

    public List<Reels> getList() {
        return reels;
    }

    public interface OnVideoGridClickListner {
        void onVideoClick(int position);
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        ItemVidProfileListBinding binding;

        public FeedViewHolder(View itemView) {
            super(itemView);
            binding = ItemVidProfileListBinding.bind(itemView);
        }

        public void setData(int position) {
            Reels reel = reels.get(position);

            Glide.with(binding.getRoot()).load(reel.getVideo()).centerCrop().into(binding.imgThumb);
            binding.tvViewCount.setText(String.valueOf(reel.getLikes()));
            binding.getRoot().setOnClickListener(v -> onVideoGridClickListner.onVideoClick(position));
        }
    }
}
