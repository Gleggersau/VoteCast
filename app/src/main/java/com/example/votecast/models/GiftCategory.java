package com.example.votecast.models;

import java.util.List;

public class GiftCategory {
    String name;
    List<GiftRoot> giftRoot;

    public GiftCategory(String name, List<GiftRoot> giftRoot) {
        this.name = name;
        this.giftRoot = giftRoot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GiftRoot> getGiftRoot() {
        return giftRoot;
    }

    public void setGiftRoot(List<GiftRoot> giftRoot) {
        this.giftRoot = giftRoot;
    }
}
