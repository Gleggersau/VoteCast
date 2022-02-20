package com.example.votecast.models;

import android.graphics.drawable.Drawable;

import java.util.UUID;

public class EmojiRoot {
    UUID id;
    Drawable drawable;
    int coin;

    public EmojiRoot(String id, Drawable drawable, int coin) {
        this.id = UUID.randomUUID();
        ;
        this.drawable = drawable;
        this.coin = coin;
    }
}
