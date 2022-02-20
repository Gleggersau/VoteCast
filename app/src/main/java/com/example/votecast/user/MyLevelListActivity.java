package com.example.votecast.user;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityMyLevelListBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyLevelListActivity extends BaseActivity {
    ActivityMyLevelListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_level_list);


        List<LevelRoot> list = new ArrayList<>(Arrays.asList(
                new LevelRoot(R.drawable.lv1, "Above spent 2000", 1),
                new LevelRoot(R.drawable.lv2, "Above spent 5000", 2),
                new LevelRoot(R.drawable.lv3, "Above spent 10000", 3),
                new LevelRoot(R.drawable.lv4, "Above spent 50000", 4),
                new LevelRoot(R.drawable.lv5, "Above spent 100000", 5)
        ));
        binding.rvFeed.setAdapter(new LevelsAdapter(list));
    }

    public class LevelRoot {
        int image;
        int level;
        String coin;

        public LevelRoot(int image, String coin, int level) {
            this.image = image;
            this.coin = coin;
            this.level = level;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}