package com.example.votecast;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "drafts")
public class Draft implements Parcelable {

    public static final Creator<Draft> CREATOR = new Creator<Draft>() {

        @Override
        public Draft createFromParcel(Parcel in) {
            return new Draft(in);
        }

        @Override
        public Draft[] newArray(int size) {
            return new Draft[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String video;
    public String screenshot;
    public String preview;
    @Nullable
    public String description;
    @Nullable
    public String language;
    @ColumnInfo(name = "is_private")
    public boolean isPrivate;
    @ColumnInfo(name = "has_comments")
    public boolean hasComments;
    @Nullable
    public String location;
    @Nullable
    public Double latitude;
    @Nullable
    public Double longitude;
    @ColumnInfo(name = "song_id")
    @Nullable
    public Integer songId;

    public Draft() {
    }

    protected Draft(Parcel in) {
        id = in.readInt();
        video = in.readString();
        screenshot = in.readString();
        preview = in.readString();
        description = in.readString();
        language = in.readString();
        isPrivate = in.readByte()!=0;
        hasComments = in.readByte()!=0;
        location = in.readString();
        if (in.readByte()==0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte()==0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        if (in.readByte()==0) {
            songId = null;
        } else {
            songId = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(video);
        dest.writeString(screenshot);
        dest.writeString(preview);
        dest.writeString(description);
        dest.writeString(language);
        dest.writeByte((byte) (isPrivate ? 1 : 0));
        dest.writeByte((byte) (hasComments ? 1 : 0));
        dest.writeString(location);
        if (latitude==null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude==null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        if (songId==null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(songId);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
