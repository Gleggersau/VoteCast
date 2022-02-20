package com.example.votecast.livestreamming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.databinding.ItemVideoGridBinding;
import com.example.votecast.models.User;
import com.example.votecast.retrofit.Const;
import com.example.votecast.z_demo.Demo_contents;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder> {

    private Context context;
    private List<User> users = new ArrayList<>();

    @Override
    public VideoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new VideoListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(VideoListAdapter.VideoListViewHolder holder, int position) {
        holder.setData(position);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addData(List<User> users) {

        this.users.addAll(users);
        notifyItemRangeInserted(this.users.size(), users.size());
    }

    public class VideoListViewHolder extends RecyclerView.ViewHolder {
        ItemVideoGridBinding binding;

        public VideoListViewHolder(View itemView) {
            super(itemView);
            binding = ItemVideoGridBinding.bind(itemView);


        }

        public void setData(int position) {
            User user = users.get(position);
            binding.tvName.setText(user.getName());
            binding.tvCountry.setText(user.getCountry());
            binding.tvViewCount.setText(String.valueOf(Demo_contents.getRandomCoin()));
            Glide.with(context).load(user.getImage()).centerCrop().into(binding.image);
            binding.getRoot().setOnClickListener(v -> context.startActivity(new Intent(context, WatchLiveActivity.class).putExtra(Const.USER_STR, new Gson().toJson(user))));

        }
    }
}
