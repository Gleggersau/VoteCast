package com.example.votecast.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.votecast.BuildConfig;
import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.bottomsheets.BottomSheetGender;
import com.example.votecast.models.User;
import com.example.votecast.retrofit.Const;
import com.example.votecast.z_demo.Demo_contents;

public class LoginActivityActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
    }

    public void onClickGoogle(View view) {
        new BottomSheetGender(LoginType.QUICK, this, (g, name) -> {

            if (g.isEmpty()) {
                Toast.makeText(LoginActivityActivity.this, "Select Gender First", Toast.LENGTH_SHORT).show();
                return;
            }

            attemptLogin(name, g);
        });

    }

    private void attemptLogin(String name, String gender) {
        String imageUrl;
        if (gender.equals("MALE")) {
            imageUrl = BuildConfig.BASE_URL + "storage/male.png";
        } else {
            imageUrl = BuildConfig.BASE_URL + "storage/female.png";

        }

        User user = new User(name, "I am guest User", "@" + name, name + "@email.com", imageUrl, "INDIA", 1, 10, 10, 5, 27, Demo_contents.getRandomCoin(), 5, 99, gender);

        SessionManager sessionManager = new SessionManager(this);
        sessionManager.saveUser(user);
        sessionManager.saveBooleanValue(Const.ISLOGIN, true);


        startActivity(new Intent(this, MainActivity.class));
    }

    public enum LoginType {
        GOOGLE, FACEBOOK, QUICK, MOBILE;
    }
}