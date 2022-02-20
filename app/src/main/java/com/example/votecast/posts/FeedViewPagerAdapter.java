package com.example.votecast.posts;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FeedViewPagerAdapter extends FragmentPagerAdapter {


    public FeedViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new FeedListFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
