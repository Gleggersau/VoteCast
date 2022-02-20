package com.example.votecast.livestreamming;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraX;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.VideoCaptureConfig;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.databinding.ActivityGotoLiveBinding;
import com.example.votecast.utils.AutoFitPreviewBuilder;

public class GotoLiveActivity extends AppCompatActivity {
    ActivityGotoLiveBinding binding;
    int front = 1;
    int back = 2;
    int camara = front;
    boolean isPrivate = false;
    PreviewConfig.Builder builder;
    PreviewConfig previewConfig;
    Preview preview;
    VideoCaptureConfig.Builder builder1;
    VideoCaptureConfig videoCaptureConfig;
    VideoCapture videoCapture;
    private CameraX.LensFacing lensFacing = CameraX.LensFacing.FRONT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_goto_live);
        initCamera();
        initListner();
    }

    private void initListner() {
        binding.btnSwitchCamara.setOnClickListener(v -> {
            if (camara==front) {
                camara = back;
                lensFacing = CameraX.LensFacing.BACK;
            } else {
                camara = front;
                lensFacing = CameraX.LensFacing.FRONT;
            }
            CameraX.unbindAll();
            initCamera();
        });
        binding.lytPrivacy.setOnClickListener(v -> {
            isPrivate = !isPrivate;

            if (isPrivate) {
                binding.imgLock.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lock));
                binding.tvPrivacy.setText("Private");

            } else {
                binding.imgLock.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.unlock));
                binding.tvPrivacy.setText("Public");
            }
        });
        binding.btnClose.setOnClickListener(v -> onBackPressed());
        binding.btnLive.setOnClickListener(v -> {
            startActivity(new Intent(this, HostLiveActivity.class));
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
            builder = new PreviewConfig.Builder();
            previewConfig = builder.setTargetAspectRatio(ratio)
                    .setLensFacing(lensFacing)
                    .setTargetRotation(Surface.ROTATION_90)
                    .build();
            preview = AutoFitPreviewBuilder.Companion.build(previewConfig, viewFinder);
            builder1 = new VideoCaptureConfig.Builder();
            videoCaptureConfig = builder1.setTargetAspectRatio(ratio)
                    .setLensFacing(lensFacing)
                    .setVideoFrameRate(24)
                    .setTargetRotation(Surface.ROTATION_0)
                    .build();
            videoCapture = new VideoCapture(videoCaptureConfig);
            CameraX.bindToLifecycle(this, preview, videoCapture);
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
}