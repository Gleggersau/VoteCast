package com.example.votecast.livestreamming;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraX;
import androidx.camera.core.PreviewConfig;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.VideoCaptureConfig;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.bottomsheets.UserProfileBottomSheet;
import com.example.votecast.databinding.ActivityHostLiveBinding;
import com.example.votecast.emoji.EmojiBottomsheetFragment;
import com.example.votecast.models.LiveStramComment;
import com.example.votecast.utils.AutoFitPreviewBuilder;
import com.example.votecast.viewmodel.HostLiveViewModel;
import com.example.votecast.viewmodel.ViewModelFactory;
import com.example.votecast.z_demo.Demo_contents;

public class HostLiveActivity extends AppCompatActivity {
    public static final String TAG = "hostliveactivity";
    ActivityHostLiveBinding binding;
    private HostLiveViewModel viewModel;
    SessionManager sessionManager;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {


            viewModel.liveStramCommentAdapter.addSingleComment(Demo_contents.getLiveStreamComment().get(0));
            binding.rvComments.scrollToPosition(viewModel.liveStramCommentAdapter.getItemCount() - 1);
            handler.postDelayed(this, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_host_live);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new HostLiveViewModel()).createFor()).get(HostLiveViewModel.class);
        sessionManager = new SessionManager(this);
        binding.setViewModel(viewModel);


        viewModel.initLister();
        initCamera();
        initLister();

        viewModel.liveStramCommentAdapter.addSingleComment(new LiveStramComment("", Demo_contents.getUsers(true).get(0), true));
        handler.postDelayed(runnable, 2000);

    }

    private void initLister() {
        viewModel.isShowFilterSheet.observe(this, aBoolean -> {
            Log.d(TAG, "initLister:filter sheet  " + aBoolean);
            if (Boolean.TRUE.equals(aBoolean)) {
                binding.lytFilters.setVisibility(View.VISIBLE);
            } else {
                binding.lytFilters.setVisibility(View.GONE);
            }
        });
        viewModel.selectedFilter.observe(this, selectedFilter -> {
            if (selectedFilter.getFilter()==0) {
                Log.d(TAG, "initLister: null");
                binding.imgFilter.setImageDrawable(null);
            } else {
                Log.d(TAG, "initLister: ffff");
                Glide.with(this).asGif().load(selectedFilter.getFilter()).into(binding.imgFilter);
            }
            Log.d(HostLiveActivity.TAG + " ", "onBindViewHolder: 11===========" + selectedFilter.getTitle());
        });
        viewModel.selectedFilter2.observe(this, selectedFilter -> {
            if (selectedFilter.getFilter()==0) {
                Log.d(TAG, "initLister: null");
                binding.imgFilter2.setImageDrawable(null);
            } else {
                Log.d(TAG, "initLister: ffff");
                Glide.with(this).load(selectedFilter.getFilter()).into(binding.imgFilter2);
            }
            Log.d(HostLiveActivity.TAG + " ", "onBindViewHolder: 11===========" + selectedFilter.getTitle());
        });
        viewModel.selectedSticker.observe(this, selectedFilter -> {
            binding.imgSticker.setImageURI(selectedFilter.image);

            binding.imgSticker.setVisibility(View.VISIBLE);
            new Handler(Looper.myLooper()).postDelayed(() -> binding.imgSticker.setVisibility(View.GONE), 2000);

        });
        viewModel.clickedComment.observe(this, user -> new UserProfileBottomSheet(this, true, user));
        viewModel.clickedUser.observe(this, user -> new UserProfileBottomSheet(this, false, user));
        binding.btnClose.setOnClickListener(v -> {
            startActivity(new Intent(this, LiveSummaryActivity.class));
            finish();
        });

    }


    @SuppressLint("RestrictedApi")
    private void initCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    1);
        } else {
            TextureView viewFinder = binding.viewFinder;
            AspectRatio ratio = AspectRatio.RATIO_4_3;
            viewModel.builder = new PreviewConfig.Builder();
            viewModel.previewConfig = viewModel.builder.setTargetAspectRatio(ratio)
                    .setLensFacing(viewModel.lensFacing)
                    .setTargetRotation(Surface.ROTATION_90)
                    .build();
            viewModel.preview = AutoFitPreviewBuilder.Companion.build(viewModel.previewConfig, viewFinder);
            viewModel.builder1 = new VideoCaptureConfig.Builder();
            viewModel.videoCaptureConfig = viewModel.builder1.setTargetAspectRatio(ratio)
                    .setLensFacing(viewModel.lensFacing)
                    .setVideoFrameRate(24)
                    .setTargetRotation(Surface.ROTATION_0)
                    .build();
            viewModel.videoCapture = new VideoCapture(viewModel.videoCaptureConfig);
            CameraX.bindToLifecycle(this, viewModel.preview, viewModel.videoCapture);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        initCamera();
    }


    public void onClickFilter(View view) {
        viewModel.isShowFilterSheet.setValue(true);
        binding.rvFilters.setAdapter(viewModel.filterAdapter_tt);

    }

    public void onSwitchCameraClicked(View view) {
        if (viewModel.lensFacing==CameraX.LensFacing.FRONT) {
            viewModel.lensFacing = CameraX.LensFacing.BACK;
        } else {
            viewModel.lensFacing = CameraX.LensFacing.FRONT;
        }
        CameraX.unbindAll();
        initCamera();
    }

    public void onClickGifIcon(View view) {
        viewModel.isShowFilterSheet.setValue(true);
        binding.rvFilters.setAdapter(viewModel.filterAdapter2);
    }

    public void onClickStickerIcon(View view) {
        viewModel.isShowFilterSheet.setValue(true);
        binding.rvFilters.setAdapter(viewModel.stickerAdapter);
    }

    public void onClickEmojiIcon(View view) {
        Log.d(TAG, "onClickEmojiIcon:1 ");
    }

    public void onLocalAudioMuteClicked(View view) {
        Log.d(TAG, "onClickEmojiIcon: ");
    }

    public void onclickGiftIcon(View view) {
        new EmojiBottomsheetFragment().show(getSupportFragmentManager(), "emojifragfmetn");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    public void onClickSendComment(View view) {
        String comment = binding.etComment.getText().toString();
        if (!comment.isEmpty()) {
            LiveStramComment liveStramComment = new LiveStramComment(comment, sessionManager.getUser(), false);
            viewModel.liveStramCommentAdapter.addSingleComment(liveStramComment);
            binding.rvComments.scrollToPosition(viewModel.liveStramCommentAdapter.getItemCount() - 1);
            binding.etComment.setText("");
        }
    }

    public void onclickShare(View view) {
        Log.d(TAG, "onClickEmojiIcon: ");
    }

    ///   filter  gift sticker emoji
}