package com.example.votecast.livestreamming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemFiltersBinding;
import com.example.votecast.utils.filters.FilterRoot;
import com.example.votecast.utils.filters.FilterUtils;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {
    List<FilterRoot> filters = FilterUtils.getFilters();
    private Context context;
    private OnFilterClickListnear onFilterClickListnear;


    public OnFilterClickListnear getOnFilterClickListnear() {
        return onFilterClickListnear;
    }

    public void setOnFilterClickListnear(OnFilterClickListnear onFilterClickListnear) {
        this.onFilterClickListnear = onFilterClickListnear;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new FilterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_filters, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {

        FilterRoot filterRoot = filters.get(position);
        if (filterRoot.getFilter() == 0) {
            holder.binding.imgf1.setImageDrawable(null);
            holder.binding.imgf2.setVisibility(View.VISIBLE);
        } else {
            holder.binding.imgf1.setImageDrawable(ContextCompat.getDrawable(context, filterRoot.getFilter()));
        }
        holder.binding.tvfiltername.setText(filterRoot.getTitle());


        holder.binding.imgf1.setOnClickListener(v -> {
            Log.d("TAG", "onBindViewHolder: ===========" + filterRoot.getTitle());
            onFilterClickListnear.onFilterClick(filterRoot);
        });

    }


    @Override
    public int getItemCount() {
        return filters.size();
    }

    public interface OnFilterClickListnear {
        void onFilterClick(FilterRoot filterRoot);
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {
        ItemFiltersBinding binding;

        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemFiltersBinding.bind(itemView);
        }
    }
}
