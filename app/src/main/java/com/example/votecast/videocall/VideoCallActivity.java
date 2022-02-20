package com.example.votecast.videocall;

import static com.google.android.exoplayer2.Player.STATE_BUFFERING;
import static com.google.android.exoplayer2.Player.STATE_ENDED;
import static com.google.android.exoplayer2.Player.STATE_IDLE;
import static com.google.android.exoplayer2.Player.STATE_READY;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

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

import com.example.votecast.R;
import com.example.votecast.databinding.ActivityVideoCallBinding;
import com.example.votecast.utils.AutoFitPreviewBuilder;
import com.example.votecast.viewmodel.VideoCallViewModel;
import com.example.votecast.viewmodel.ViewModelFactory;
import com.example.votecast.z_demo.Demo_contents;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class VideoCallActivity extends AppCompatActivity {
    private static final String TAG = "videocallact";
    ActivityVideoCallBinding binding;
    private VideoCallViewModel viewModel;
    private SimpleExoPlayer player;
    String videoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_call);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new VideoCallViewModel()).createFor()).get(VideoCallViewModel.class);
        initCamera();
        initVideo();

        binding.btnDecline.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) {

            return;
        }
        initCamera();
    }

    public MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    private void initVideo() {

        videoURL = Demo_contents.getFemaleVideos().get(0);
        player = new SimpleExoPlayer.Builder(this).build();
        binding.playerview.setPlayer(player);
        Log.d(TAG, "setvideoURL: " + videoURL);
        Uri uri = Uri.parse(videoURL);
        MediaSource mediaSource = buildMediaSource(uri);
        Log.d(TAG, "initializePlayer: " + uri);
        player.setPlayWhenReady(true);
        player.seekTo(0, 0);
        player.prepare(mediaSource, false, false);
        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState) {

                    case STATE_BUFFERING:
                        Log.d(TAG, "buffer: " + uri);
                        break;
                    case STATE_ENDED:
                        Toast.makeText(VideoCallActivity.this, "Call Ended!", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(VideoCallActivity.this::finish, 2000);
                        Log.d(TAG, "end: " + uri);
                        break;
                    case STATE_IDLE:
                        Log.d(TAG, "idle: " + uri);

                        break;

                    case STATE_READY:


                        Log.d(TAG, "ready: " + uri);

                        break;
                    default:
                        break;
                }
            }
        });

    }


    @SuppressLint("RestrictedApi")
    private void initCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) {

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
    protected void onPause() {
        super.onPause();
        if (player!=null) {
            player.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player!=null) {
            player.setPlayWhenReady(true);
        }
    }

    @Override
    protected void onDestroy() {
        CameraX.unbindAll();
        if (player!=null) {
            player.release();
        }
        super.onDestroy();
    }

    public void onSwitchCameraClicked(View view) {

        if (viewModel.lensFacing==CameraX.LensFacing.FRONT) {
            viewModel.lensFacing = CameraX.LensFacing.BACK;
        } else {
            viewModel.lensFacing = CameraX.LensFacing.FRONT;
        }
        CameraX.unbindAll();
        new Handler(Looper.getMainLooper()).postDelayed(this::initCamera, 1000);

    }

}