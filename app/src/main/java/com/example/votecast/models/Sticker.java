package com.example.votecast.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Sticker implements Parcelable {

    public static final Creator<Sticker> CREATOR = new Creator<Sticker>() {

        @Override
        public Sticker createFromParcel(Parcel in) {
            return new Sticker(in);
        }

        @Override
        public Sticker[] newArray(int size) {
            return new Sticker[size];
        }
    };
    public int id;
    public String image;

    public Sticker() {
    }

    public Sticker(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Sticker{" +
                "id=" + id +
                ", image='" + image + '\'' +

                '}';
    }

    protected Sticker(Parcel in) {
        id = in.readInt();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(image);
    }
}
