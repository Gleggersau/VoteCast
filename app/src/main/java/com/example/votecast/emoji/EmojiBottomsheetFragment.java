package com.example.votecast.emoji;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.votecast.R;
import com.example.votecast.databinding.FragmentEmojiBottomsheetBinding;
import com.example.votecast.databinding.ItemEmojiGridBinding;
import com.example.votecast.models.GiftCategory;
import com.example.votecast.viewmodel.EmojiSheetViewModel;
import com.example.votecast.viewmodel.ViewModelFactory;
import com.example.votecast.z_demo.Demo_contents;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class EmojiBottomsheetFragment extends BottomSheetDialogFragment {


    FragmentEmojiBottomsheetBinding binding;
    String[] country = {" 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", " 10"};
    private EmojiViewPagerAdapter emojiViewPagerAdapter;
    private ItemEmojiGridBinding lastBinding = null;

    List<GiftCategory> giftCategories = Demo_contents.getGiftCategory();
    public EmojiBottomsheetFragment() {
        // Required empty public constructor
    }

    EmojiSheetViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emoji_bottomsheet, container, false);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new EmojiSheetViewModel()).createFor()).get(EmojiSheetViewModel.class);
        initMain();
        initListner();
        return binding.getRoot();


    }


    private void initListner() {
        emojiViewPagerAdapter.setOnEmojiSelectLister((binding1, giftRoot) -> {
            if (lastBinding!=null) {
                lastBinding.itememoji.setBackground(null);
                binding1.itememoji.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bg_selected_5dp));
            }
            lastBinding = binding1;
        });


        ArrayAdapter<String> aa = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, country);

        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(aa);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.tvGiftCount.setText(String.valueOf(country[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.tvGiftCount.setText(String.valueOf(1));
            }
        });
        binding.lytGiftCount.setOnClickListener(v -> binding.spinner.performClick());
    }




    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialogTheme;
    }

    private void initMain() {
        emojiViewPagerAdapter = new EmojiViewPagerAdapter(getChildFragmentManager(), giftCategories);
        binding.viewPager.setAdapter(emojiViewPagerAdapter);
        binding.tablayout1.setupWithViewPager(binding.viewPager);
        binding.tablayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //ll

                View v = tab.getCustomView();
                if (v != null) {
                    TextView tv = (TextView) v.findViewById(R.id.tvTab);
                    tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                    tv.setTextSize(16);

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Typeface typeface = getResources().getFont(R.font.abold);
                        tv.setTypeface(typeface);
                    }
                    View indicator = (View) v.findViewById(R.id.indicator);
                    indicator.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //ll
                View v = tab.getCustomView();
                if (v != null) {
                    TextView tv = (TextView) v.findViewById(R.id.tvTab);
                    tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.graylight));
                    tv.setTextSize(14);
                    View indicator = (View) v.findViewById(R.id.indicator);
                    indicator.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//ll
            }
        });
        settab(giftCategories);


    }

    private void settab(List<GiftCategory> contry) {
        binding.tablayout1.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tablayout1.removeAllTabs();
        for (int i = 0; i < contry.size(); i++) {
            binding.tablayout1.addTab(binding.tablayout1.newTab().setCustomView(createCustomView(i, contry.get(i).getName())));
        }

    }

    private View createCustomView(int i, String s) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tabhorizontol2, null);
        TextView tv = (TextView) v.findViewById(R.id.tvTab);
        tv.setText(s);
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.graylight));
        tv.setTextSize(14);
        View indicator = (View) v.findViewById(R.id.indicator);
        if (i == 0) {
            indicator.setVisibility(View.VISIBLE);
        } else {
            indicator.setVisibility(View.GONE);
        }
        return v;

    }
}