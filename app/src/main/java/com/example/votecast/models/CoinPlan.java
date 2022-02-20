package com.example.votecast.models;

public class CoinPlan {
    int coin;
    long amount;
    String label;

    public CoinPlan() {
    }

    public CoinPlan(int coin, long amount, String label) {
        this.coin = coin;
        this.amount = amount;
        this.label = label;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
