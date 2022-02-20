package com.example.votecast.models;

public class Post {
    String lication, time, caption, image;
    User user;
    int commentCount, likeCount;

    public Post(String lication, String time, String caption, String image, User user, int commentCount, int likeCount) {
        this.lication = lication;
        this.time = time;
        this.caption = caption;
        this.image = image;
        this.user = user;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }

    public String getLication() {
        return lication;
    }

    public String getTime() {
        return time;
    }

    public String getCaption() {
        return caption;
    }

    public String getImage() {
        return image;
    }

    public User getUser() {
        return user;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }
}
