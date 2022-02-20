package com.example.votecast.models;

public class LiveStramComment {
    String comment;
    User user;
    boolean isJoined = false;

    public LiveStramComment() {
    }

    public LiveStramComment(String comment, User user, boolean isJoined) {
        this.comment = comment;
        this.user = user;
        this.isJoined = isJoined;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }
}
