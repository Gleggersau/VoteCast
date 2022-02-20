package com.example.votecast;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.multidex.MultiDex;

import com.arthenica.mobileffmpeg.Config;
import com.example.votecast.providers.ExoPlayerProvider;
import com.example.votecast.providers.JacksonProvider;
import com.example.votecast.providers.RoomProvider;
import com.example.votecast.utils.TempUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.pixplicity.easyprefs.library.Prefs;
import com.vaibhavpandey.katora.Container;
import com.vaibhavpandey.katora.contracts.ImmutableContainer;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.facebook.FacebookEmojiProvider;
import com.vanniktech.emoji.google.GoogleEmojiProvider;
import com.vanniktech.emoji.ios.IosEmojiProvider;
import com.vanniktech.emoji.twitter.TwitterEmojiProvider;

import java.util.Collections;


public class MainApplication extends Application {

    private static final Container CONTAINER = new Container();
    private static final String TAG = "MainApplication";

    public static ImmutableContainer getContainer() {
        return CONTAINER;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressWarnings("SameParameterValue")
    private void createChannel(String id, String name, int visibility, int importance) {
        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.enableLights(true);
        channel.setLightColor(ContextCompat.getColor(this, R.color.colorPrimary));
        channel.setLockscreenVisibility(visibility);
        if (importance==NotificationManager.IMPORTANCE_LOW) {
            channel.setShowBadge(false);
        }

        NotificationManager nm =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.createNotificationChannel(channel);
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Config.enableLogCallback(message -> Log.d(TAG, message.getText()));
        Config.enableStatisticsCallback(stats ->
                Log.d(TAG, String.format(
                        "FFmpeg frame: %d, time: %d", stats.getVideoFrameNumber(), stats.getTime())));
        Fresco.initialize(this);
        if (BuildConfig.DEBUG) {
            RequestConfiguration configuration = new RequestConfiguration.Builder()
                    .setTestDeviceIds(Collections.singletonList(getString(R.string.admob_test_device_id)))
                    .build();
            MobileAds.setRequestConfiguration(configuration);
        }

        MobileAds.initialize(this, status -> { /* eaten */ });
        int emoji = getResources().getInteger(R.integer.emoji_variant);
        switch (emoji) {
            case 1:
                EmojiManager.install(new GoogleEmojiProvider());
                break;
            case 2:
                EmojiManager.install(new FacebookEmojiProvider());
                break;
            case 3:
                EmojiManager.install(new TwitterEmojiProvider());
                break;
            default:
                EmojiManager.install(new IosEmojiProvider());
                break;
        }


        new Prefs.Builder()
                .setContext(this)
                .setUseDefaultSharedPreference(true)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(
                    getString(R.string.notification_channel_id),
                    getString(R.string.notification_channel_name),
                    Notification.VISIBILITY_PUBLIC,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
        }

        CONTAINER.install(new ExoPlayerProvider(this));
        CONTAINER.install(new JacksonProvider());
        CONTAINER.install(new RoomProvider(this));
        TempUtil.cleanupStaleFiles(getApplicationContext());
    }


}
