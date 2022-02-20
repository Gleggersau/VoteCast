package com.example.votecast.user.wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemPurchaseCoinBinding;
import com.example.votecast.models.CoinPlan;
import com.example.votecast.retrofit.Const;

import java.util.ArrayList;
import java.util.List;

public class CoinPurchaseAdapter extends RecyclerView.Adapter<CoinPurchaseAdapter.CoinViewHolder> {

     Context context;
    OnCoinPlanClickListner onCoinPlanClickListner;
    private List<CoinPlan> coinList = new ArrayList<>();

    public OnCoinPlanClickListner getOnCoinPlanClickListner() {
        return onCoinPlanClickListner;
    }

    public void setOnCoinPlanClickListner(OnCoinPlanClickListner onCoinPlanClickListner) {
        this.onCoinPlanClickListner = onCoinPlanClickListner;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CoinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase_coin, parent, false));
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    public void addData(List<CoinPlan> coinList) {

        this.coinList.addAll(coinList);
        notifyItemRangeInserted(this.coinList.size(), coinList.size());
    }

    public interface OnCoinPlanClickListner {
        void onPlanClick(CoinPlan coinPlan);
    }

    public class CoinViewHolder extends RecyclerView.ViewHolder {
        ItemPurchaseCoinBinding binding;

        public CoinViewHolder(View itemView) {
            super(itemView);
            binding = ItemPurchaseCoinBinding.bind(itemView);
        }

        public void setData(int position) {
            CoinPlan coinPlan = coinList.get(position);
            binding.tvCoin.setText(String.valueOf(coinPlan.getCoin()));
            binding.tvLabel.setVisibility(coinPlan.getLabel().isEmpty() ? View.GONE : View.VISIBLE);
            binding.tvLabel.setText(coinPlan.getLabel());
            binding.tvAmount.setText(String.valueOf(coinPlan.getAmount()) + Const.CURRENCY);
            binding.getRoot().setOnClickListener(v -> onCoinPlanClickListner.onPlanClick(coinPlan));
        }
    }
}
