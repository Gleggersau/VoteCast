package com.example.votecast.purchase;

import android.app.Activity;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetailsParams;

import java.util.ArrayList;
import java.util.List;


public class Myplaystorebilling {

    private static final String TAG = "purchased";
    private final BillingClient billingClient;
    private final Activity activity;
    private final OnPurchaseComplete onPurchaseComplete;
    boolean isConsumable = false;
    boolean isConnected = false;

    public Myplaystorebilling(Activity activity, OnPurchaseComplete onPurchaseComplete) {

        this.activity = activity;
        this.onPurchaseComplete = onPurchaseComplete;

        PurchasesUpdatedListener purchasesUpdatedListener = (billingResult, purchases) -> {
            // To be implemented in a later section.
            Log.d(TAG, "onPurchasesUpdated: 1");
            if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK
                    && purchases!=null) {
                Log.d(TAG, "onPurchasesUpdated: size  " + purchases.size());
                if (!purchases.isEmpty()) {

                    handlePurchase(purchases.get(0));

                }

            } else if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.USER_CANCELED) {
                // Handle an error caused by a user cancelling the purchase flow.
                onPurchaseComplete.onPurchaseResult(false);
            } else {
                // Handle any other error codes.
                onPurchaseComplete.onPurchaseResult(false);
            }
        };

        billingClient = BillingClient.newBuilder(activity)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    Log.d(TAG, "onBillingSetupFinished: ");
                    isConnected = true;
                    onPurchaseComplete.onConnected(true);
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                Log.d(TAG, "onBillingServiceDisconnected: ");
                isConnected = false;
            }
        });

    }

    void handlePurchase(Purchase purchase) {

        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        onPurchaseComplete.onPurchaseResult(true);


        ConsumeResponseListener listener = (billingResult, purchaseToken) -> {
            if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK) {


                // Handle the success of the consume operation.
            }
        };

        billingClient.consumeAsync(consumeParams, listener);

    }


    public void startPurchase(String productId, String skuType, boolean isConsumable) {
        if (isConnected) {
            Log.d(TAG, "startPurchase: " + productId);
            this.isConsumable = isConsumable;
            List<String> skuList = new ArrayList<>();
            skuList.add(productId);
            SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
            params.setSkusList(skuList).setType(skuType);
            billingClient.querySkuDetailsAsync(params.build(),
                    (billingResult1, skuDetailsList) -> {
                        // Process the result.
                        BillingFlowParams billingFlowParams = null;
                        if (skuDetailsList!=null) {
                            Log.d("TAG", "startPurchase: " + skuDetailsList.get(0));
                            billingFlowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(skuDetailsList.get(0))
                                    .build();
                        }
                        if (billingFlowParams!=null) {
                            billingClient.launchBillingFlow(activity, billingFlowParams);
                        }

                    });

        } else {
            Log.d(TAG, "startPurchase: sdsd");
        }
    }

    public boolean isSubscriptionRunning() {
        return billingClient.queryPurchases(BillingClient.SkuType.SUBS).getPurchasesList()!=null
                && !billingClient.queryPurchases(BillingClient.SkuType.SUBS).getPurchasesList().isEmpty();
    }

    public void onDestroy() {
        if (isConnected)
            billingClient.endConnection();
    }

    public interface OnPurchaseComplete {

        void onConnected(boolean isConnect);

        void onPurchaseResult(boolean isPurchaseSuccess);
    }
}
