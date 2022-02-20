package com.example.votecast.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ClipSection implements Parcelable {

    public static final Creator<ClipSection> CREATOR = new Creator<ClipSection>() {

        @Override
        public ClipSection createFromParcel(Parcel in) {
            return new ClipSection(in);
        }

        @Override
        public ClipSection[] newArray(int size) {
            return new ClipSection[size];
        }
    };
    public int id;
    public String name;
    public Date createdAt;
    public Date updatedAt;
    public int clipsCount;

    public ClipSection() {
    }

    protected ClipSection(Parcel in) {
        id = in.readInt();
        name = in.readString();
        clipsCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(clipsCount);
    }
}
