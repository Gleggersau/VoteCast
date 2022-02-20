package com.example.votecast.user.vip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemVipPlanBinding;

public class VipPlanAdapter extends RecyclerView.Adapter<VipPlanAdapter.VipPlanViewHolder> {

    private Context context;
    private int selected = 0;

    @Override
    public VipPlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new VipPlanViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vip_plan, parent, false));
    }

    @Override
    public void onBindViewHolder(VipPlanViewHolder holder, int position) {

        if (selected == position) {
            holder.binding.lytBack.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.pink));
            holder.binding.tvDays.setTextColor(ContextCompat.getColor(context, R.color.pink));
            holder.binding.tvDaysString.setTextColor(ContextCompat.getColor(context, R.color.pink));
            holder.binding.tvDes.setTextColor(ContextCompat.getColor(context, R.color.pink));
        } else {
            holder.binding.lytBack.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.grayinsta));
            holder.binding.tvDays.setTextColor(ContextCompat.getColor(context, R.color.grayinsta));
            holder.binding.tvDaysString.setTextColor(ContextCompat.getColor(context, R.color.grayinsta));
            holder.binding.tvDes.setTextColor(ContextCompat.getColor(context, R.color.grayinsta));
        }
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class VipPlanViewHolder extends RecyclerView.ViewHolder {
        ItemVipPlanBinding binding;

        public VipPlanViewHolder(View itemView) {
            super(itemView);
            binding = ItemVipPlanBinding.bind(itemView);
        }

        public void setData(int position) {

            binding.getRoot().setOnClickListener(v -> {
                selected = position;
                notifyDataSetChanged();
            });
        }
    }
}
