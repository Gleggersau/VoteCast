package com.example.votecast.models;

public class Comment {
    User user;
    String date, comment;

    public Comment() {

    }

    public Comment(User user, String date, String comment) {
        this.user = user;
        this.date = date;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
