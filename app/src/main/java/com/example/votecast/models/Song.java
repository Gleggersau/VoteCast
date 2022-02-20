package com.example.votecast.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.Date;

public class Song implements Parcelable {


    public int id;
    public String title;
    @Nullable
    public String artist;
    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
    public String audio;
    public int duration;
    @Nullable
    public String details;
    public Date createdAt;
    public Date updatedAt;
    @Nullable
    public String albumImage;

    public Song() {
    }


    public Song(int id, String title, @Nullable String artist, @Nullable String album, String audio, int duration, String details) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.albumImage = album;
        this.audio = audio;

        this.duration = duration;
        this.details = details;


    }

    protected Song(Parcel in) {
        id = in.readInt();
        title = in.readString();
        artist = in.readString();
        albumImage = in.readString();
        audio = in.readString();
        duration = in.readInt();
        details = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(albumImage);
        dest.writeString(audio);
        dest.writeInt(duration);
        dest.writeString(details);
    }
}
