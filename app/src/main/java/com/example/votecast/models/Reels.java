package com.example.votecast.models;

public class Reels {
    User user;
    String caption, video;
    int likes, comments;

    public Reels() {
    }

    public Reels(User user, String caption, String video, int likes, int comments) {
        this.user = user;
        this.caption = caption;
        this.video = video;
        this.likes = likes;
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
