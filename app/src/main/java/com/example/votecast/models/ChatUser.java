package com.example.votecast.models;

public class ChatUser {
    User user;
    String time;
    String message;

    public ChatUser() {
    }

    public ChatUser(User user, String time, String message) {
        this.user = user;
        this.time = time;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
