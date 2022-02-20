package com.example.votecast.user.wallet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.databinding.ItemReedemHistoryBinding;

public class ReedemHistoryAdapter extends RecyclerView.Adapter<ReedemHistoryAdapter.ReedemHistoryViewHolder> {

    Context context;
    int selectedPos = 0;

    @Override
    public ReedemHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ReedemHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reedem_history, parent, false));
    }

    @Override
    public void onBindViewHolder(ReedemHistoryViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ReedemHistoryViewHolder extends RecyclerView.ViewHolder {
        ItemReedemHistoryBinding binding;

        public ReedemHistoryViewHolder(View itemView) {
            super(itemView);
            binding = ItemReedemHistoryBinding.bind(itemView);
        }


    }
}
