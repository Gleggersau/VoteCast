package com.example.votecast;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.votecast.models.User;
import com.google.gson.Gson;


public class SessionManager {
    private static final String USER_STR = "local_user";
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this.pref = context.getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        this.editor = this.pref.edit();
    }


    public void saveBooleanValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        return pref.getBoolean(key, false);
    }

    public void saveStringValue(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringValue(String key) {
        return pref.getString(key, "");
    }


    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        return pref.getInt(key, 0);
    }

    public void saveUser(User user) {
        editor.putString(USER_STR, new Gson().toJson(user));
        editor.apply();


    }

    public User getUser() {
        String userString = pref.getString(USER_STR, "");
        if (userString!=null && !userString.isEmpty()) {
            return new Gson().fromJson(userString, User.class);
        }
        return null;
    }



}
