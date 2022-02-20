package com.example.votecast.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class SongSection implements Parcelable {

    public static final Creator<SongSection> CREATOR = new Creator<SongSection>() {

        @Override
        public SongSection createFromParcel(Parcel in) {
            return new SongSection(in);
        }

        @Override
        public SongSection[] newArray(int size) {
            return new SongSection[size];
        }
    };
    public int id;
    public String name;
    public Date createdAt;
    public Date updatedAt;
    public int songsCount;

    public SongSection() {
    }

    protected SongSection(Parcel in) {
        id = in.readInt();
        name = in.readString();
        songsCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(songsCount);
    }
}
