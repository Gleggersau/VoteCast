package com.example.votecast.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class StickerSection implements Parcelable {

    public static final Creator<StickerSection> CREATOR = new Creator<StickerSection>() {

        @Override
        public StickerSection createFromParcel(Parcel in) {
            return new StickerSection(in);
        }

        @Override
        public StickerSection[] newArray(int size) {
            return new StickerSection[size];
        }
    };
    public int id;
    public String name;
    public Date createdAt;
    public Date updatedAt;
    public int stickersCount;

    public StickerSection() {
    }

    protected StickerSection(Parcel in) {
        id = in.readInt();
        name = in.readString();
        stickersCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(stickersCount);
    }
}
