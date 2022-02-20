package com.example.votecast.reels.record.sticker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityStickerPickerBinding;
import com.example.votecast.models.Sticker;
import com.example.votecast.z_demo.Demo_contents;

public class StickerPickerActivity extends BaseActivity {

    public static final String EXTRA_STICKER = "sticker";



    ActivityStickerPickerBinding binding;
    StickerAdapter stickerAdapter = new StickerAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sticker_picker);

        binding.rvSongs.setAdapter(stickerAdapter);
        stickerAdapter.addData(Demo_contents.getStickers());
        stickerAdapter.setOnSongClickListner(this::closeWithSelection);


    }


    public void closeWithSelection(Sticker sticker) {
        Intent data = new Intent();
        data.putExtra(EXTRA_STICKER, sticker);
        setResult(RESULT_OK, data);
        finish();
    }


}
