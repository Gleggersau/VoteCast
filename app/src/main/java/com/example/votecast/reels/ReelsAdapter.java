package com.example.votecast.reels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemReelsBinding;
import com.example.votecast.models.Reels;

import java.util.ArrayList;
import java.util.List;

public class ReelsAdapter extends RecyclerView.Adapter<ReelsAdapter.ReelsViewHolder> {

    private List<Reels> reels = new ArrayList<>();

    @Override
    public ReelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReelsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reels, parent, false));
    }

    OnReelsVideoAdapterListner onReelsVideoAdapterListner;

    public OnReelsVideoAdapterListner getOnReelsVideoAdapterListner() {
        return onReelsVideoAdapterListner;
    }

    public void setOnReelsVideoAdapterListner(OnReelsVideoAdapterListner onReelsVideoAdapterListner) {
        this.onReelsVideoAdapterListner = onReelsVideoAdapterListner;
    }

    @Override
    public void onBindViewHolder(ReelsAdapter.ReelsViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return reels.size();
    }

    public List<Reels> getList() {
        return reels;
    }

    public void addData(List<Reels> reels) {

        this.reels.addAll(reels);
        notifyItemRangeInserted(this.reels.size(), reels.size());
    }

    public interface OnReelsVideoAdapterListner {
        void onItemClick(ItemReelsBinding reelsBinding, int pos);

        void onClickUser(Reels reel);

        void onClickComments();
    }

    public class ReelsViewHolder extends RecyclerView.ViewHolder {
        ItemReelsBinding binding;

        public ReelsViewHolder(View itemView) {
            super(itemView);
            binding = ItemReelsBinding.bind(itemView);
        }

        public void setData(int position) {

            Reels reel = reels.get(position);

            Glide.with(binding.getRoot()).load(reel.getUser().getImage()).circleCrop().into(binding.imgUser);
            binding.tvUserName.setText(reel.getUser().getName());
            binding.tvDescreption.setText(reel.getCaption());
            binding.tvSoundName.setText("Sound Name..");
            binding.tvLikeCount.setText(String.valueOf(reel.getLikes()));
            binding.tvCommentCount.setText(String.valueOf(reel.getComments()));


            if (position==0) {
                onReelsVideoAdapterListner.onItemClick(binding, 0);
            }


            binding.imgUser.setOnClickListener(v -> onReelsVideoAdapterListner.onClickUser(reel));
            binding.imgComment.setOnClickListener(v -> onReelsVideoAdapterListner.onClickComments());
        }
    }

}
