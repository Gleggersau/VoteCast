package com.example.votecast.user.wallet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemCoinHistoryBinding;

public class CoinHistoryAdapter extends RecyclerView.Adapter<CoinHistoryAdapter.CoinHistoryViewHolder> {

    Context context;


    @Override
    public CoinHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CoinHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin_history, parent, false));
    }

    @Override
    public void onBindViewHolder(CoinHistoryViewHolder holder, int position) {
        Log.d("TAG", "setData: ");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CoinHistoryViewHolder extends RecyclerView.ViewHolder {
        ItemCoinHistoryBinding binding;

        public CoinHistoryViewHolder(View itemView) {
            super(itemView);
            binding = ItemCoinHistoryBinding.bind(itemView);
        }


    }
}
