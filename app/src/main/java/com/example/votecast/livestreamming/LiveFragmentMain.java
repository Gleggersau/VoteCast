package com.example.votecast.livestreamming;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votecast.R;
import com.example.votecast.adapter.DotAdaptr;
import com.example.votecast.databinding.FragmentLiveBinding;
import com.example.votecast.home.HomeFragment;
import com.example.votecast.home.adapter.BannerAdapter;
import com.example.votecast.reels.VideoListFragment;
import com.google.android.material.tabs.TabLayout;

public class LiveFragmentMain extends Fragment {


    FragmentLiveBinding binding;

    public LiveFragmentMain() {
        // Required empty public constructor
    }

    BannerAdapter bannerAdapter = new BannerAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_live, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.tvLive.setOnClickListener(v -> callFragment(new LiveFragmentMain()));
        binding.tvVideo.setOnClickListener(v -> callFragment(new VideoListFragment()));


        binding.rvBanner.setAdapter(bannerAdapter);
        new PagerSnapHelper().attachToRecyclerView(binding.rvBanner);
        setupLogicAutoSlider();


        binding.viewPager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
        binding.tablayout1.setupWithViewPager(binding.viewPager);
        binding.tablayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //ll

                View v = tab.getCustomView();
                if (v != null) {
                    TextView tv = (TextView) v.findViewById(R.id.tvTab);
                    tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                    tv.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.pink));
                    tv.setTextSize(16);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //ll
                View v = tab.getCustomView();
                if (v != null) {
                    TextView tv = (TextView) v.findViewById(R.id.tvTab);
                    tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_gray));
                    tv.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.grayinsta));
                    tv.setTextSize(14);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//ll
            }
        });
        settab(new String[]{"Im Trend", "Neueste", "Folge Ich"});

    }


    private void setupLogicAutoSlider() {
        DotAdaptr dotAdapter = new DotAdaptr(bannerAdapter.getItemCount(), R.color.pink);
        binding.rvDots.setAdapter(dotAdapter);
        binding.rvBanner.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager myLayoutManager = (LinearLayoutManager) binding.rvBanner.getLayoutManager();
                int scrollPosition = myLayoutManager.findFirstVisibleItemPosition();
                dotAdapter.changeDot(scrollPosition);
            }
        });
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int pos = 0;
            boolean flag = true;

            @Override
            public void run() {
                if (pos == bannerAdapter.getItemCount() - 1) {
                    flag = false;
                } else if (pos == 0) {
                    flag = true;
                }
                if (flag) {
                    pos++;
                } else {
                    pos--;
                }
                binding.rvBanner.smoothScrollToPosition(pos);
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    private void callFragment(Fragment fragment) {
        if (getParentFragment() != null) {
            ((HomeFragment) getParentFragment()).openFragmet(fragment);
        }
    }


    private void settab(String[] contry) {
        binding.tablayout1.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tablayout1.removeAllTabs();
        for (int i = 0; i < contry.length; i++) {
            binding.tablayout1.addTab(binding.tablayout1.newTab().setCustomView(createCustomView(i, contry[i])));
        }
        TabLayout tabLayout = binding.tablayout1;


        final ViewGroup test = (ViewGroup) (tabLayout.getChildAt(0));//tabs is your Tablayout
        int tabLen = test.getChildCount();

        for (int i = 0; i < tabLen; i++) {
            View v = test.getChildAt(i);
            v.setPadding(10, 0, 10, 0);
        }


    }

    private View createCustomView(int i, String s) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tabhorizontol, null);

        TextView tv = (TextView) v.findViewById(R.id.tvTab);

        tv.setText(s);
        if (i == 0) {
            tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

        } else {
            tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_gray));
            tv.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.grayinsta));

        }
        return v;

    }


}