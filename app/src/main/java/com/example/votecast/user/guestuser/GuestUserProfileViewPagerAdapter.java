package com.example.votecast.user.guestuser;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.votecast.models.User;

public class GuestUserProfileViewPagerAdapter extends FragmentPagerAdapter {


    private User user;

    public GuestUserProfileViewPagerAdapter(FragmentManager fm, User user) {
        super(fm);
        this.user = user;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0) {
            return new GuestUserPostsFragment(user);
        } else {
            return new GuestUserReelsFragment(user);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
