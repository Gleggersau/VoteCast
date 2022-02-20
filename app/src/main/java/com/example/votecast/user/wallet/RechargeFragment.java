package com.example.votecast.user.wallet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.android.billingclient.api.BillingClient;
import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.databinding.FragmentRechargeBinding;
import com.example.votecast.models.CoinPlan;
import com.example.votecast.models.User;
import com.example.votecast.purchase.Myplaystorebilling;
import com.example.votecast.z_demo.Demo_contents;

public class RechargeFragment extends Fragment {


    CoinPurchaseAdapter coinPurchaseAdapter = new CoinPurchaseAdapter();

    public RechargeFragment() {
        // Required empty public constructor
    }

    SessionManager sessionManager;
    FragmentRechargeBinding binding;
    Myplaystorebilling myplaystorebilling;
    private CoinPlan selectedPlan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recharge, container, false);
        sessionManager = new SessionManager(getActivity());
        initMain();
        initListner();

        return binding.getRoot();
    }

    private void initListner() {
        coinPurchaseAdapter.setOnCoinPlanClickListner(coinPlan -> {
            this.selectedPlan = coinPlan;
            myplaystorebilling.startPurchase("android.test.purchased", BillingClient.SkuType.INAPP, true);
        });
        myplaystorebilling = new Myplaystorebilling(getActivity(), new Myplaystorebilling.OnPurchaseComplete() {
            @Override
            public void onConnected(boolean isConnect) {
                Log.d("TAG", "onConnected: ");
            }

            @Override
            public void onPurchaseResult(boolean isPurchaseSuccess) {
                if (isPurchaseSuccess) {
                    Toast.makeText(getActivity(), "Purchased", Toast.LENGTH_SHORT).show();
                    int fienlCoin = sessionManager.getUser().getCoin() + selectedPlan.getCoin();
                    User user = sessionManager.getUser();
                    user.setCoin(fienlCoin);
                    sessionManager.saveUser(user);
                    binding.tvMyCoins.setText(String.valueOf(sessionManager.getUser().getCoin()));
                }
            }
        });
    }

    private void initMain() {
        binding.tvMyCoins.setText(String.valueOf(sessionManager.getUser().getCoin()));


        binding.rvRecharge.setAdapter(coinPurchaseAdapter);
        coinPurchaseAdapter.addData(Demo_contents.getCoinList());
    }

}