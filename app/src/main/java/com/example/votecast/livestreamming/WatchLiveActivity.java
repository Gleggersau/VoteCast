package com.example.votecast.livestreamming;

import static com.google.android.exoplayer2.Player.STATE_BUFFERING;
import static com.google.android.exoplayer2.Player.STATE_ENDED;
import static com.google.android.exoplayer2.Player.STATE_IDLE;
import static com.google.android.exoplayer2.Player.STATE_READY;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.bottomsheets.UserProfileBottomSheet;
import com.example.votecast.databinding.ActivityWatchLiveBinding;
import com.example.votecast.emoji.EmojiBottomsheetFragment;
import com.example.votecast.models.LiveStramComment;
import com.example.votecast.models.User;
import com.example.votecast.retrofit.Const;
import com.example.votecast.viewmodel.ViewModelFactory;
import com.example.votecast.viewmodel.WatchLiveViewModel;
import com.example.votecast.z_demo.Demo_contents;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.gson.Gson;

public class WatchLiveActivity extends AppCompatActivity {

    private static final String TAG = "watchliveact";
    ActivityWatchLiveBinding binding;
    private WatchLiveViewModel viewModel;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {


            viewModel.liveStramCommentAdapter.addSingleComment(Demo_contents.getLiveStreamComment().get(0));
            binding.rvComments.scrollToPosition(viewModel.liveStramCommentAdapter.getItemCount() - 1);
            handler.postDelayed(this, 2000);
        }
    };
    User host;
    private SimpleExoPlayer player;
    String videoURL;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_watch_live);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(new WatchLiveViewModel()).createFor()).get(WatchLiveViewModel.class);
        sessionManager = new SessionManager(this);
        binding.setViewModel(viewModel);
        viewModel.initLister();


        Intent intent = getIntent();
        String userStr = intent.getStringExtra(Const.USER_STR);
        if (userStr!=null && !userStr.isEmpty()) {
            host = new Gson().fromJson(userStr, User.class);

            Glide.with(this).load(host.getImage()).circleCrop().into(binding.imgProfile);
            binding.tvAge.setText(String.valueOf(host.getAge()));
            binding.tvCoins.setText(String.valueOf(host.getCoin()));
            binding.tvGifts.setText(String.valueOf(Demo_contents.getRandomPostCoint()));
            binding.tvName.setText(host.getName());
            binding.tvUserId.setText("ID: " + host.getId());

            binding.imggift2.setOnClickListener(v -> new EmojiBottomsheetFragment().show(getSupportFragmentManager(), "emojifragfmetn"));
            viewModel.liveStramCommentAdapter.addSingleComment(new LiveStramComment("", Demo_contents.getUsers(true).get(0), true));
            handler.postDelayed(runnable, 2000);


            initVideo();
            initLister();
            LiveStramComment liveStramComment = new LiveStramComment("", sessionManager.getUser(), true);
            viewModel.liveStramCommentAdapter.addSingleComment(liveStramComment);
            binding.rvComments.scrollToPosition(viewModel.liveStramCommentAdapter.getItemCount() - 1);

        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        if (player!=null) {
            player.release();
        }
    }

    private void initLister() {
        viewModel.clickedComment.observe(this, liveCommentRoot -> new UserProfileBottomSheet(WatchLiveActivity.this, false, null));
        viewModel.clickedUser.observe(this, user -> new UserProfileBottomSheet(WatchLiveActivity.this, false, user));
    }

    public MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    @SuppressLint("RestrictedApi")
    private void initVideo() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    1);
        } else {

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
                            Toast.makeText(WatchLiveActivity.this, "Live Ended!", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(WatchLiveActivity.this::finish, 2000);
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) {

            return;
        }
        initVideo();
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

    public void onClickBack(View view) {
        onBackPressed();
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
        Log.d(TAG, "onclickShare: ");
    }
}