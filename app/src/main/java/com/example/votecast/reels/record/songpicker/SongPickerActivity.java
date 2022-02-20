package com.example.votecast.reels.record.songpicker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.votecast.R;
import com.example.votecast.SharedConstants;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivitySongPickerBinding;
import com.example.votecast.models.Song;
import com.example.votecast.reels.record.workers.FileDownloadWorker;
import com.example.votecast.utils.IntentUtil;
import com.example.votecast.utils.TempUtil;
import com.example.votecast.z_demo.Demo_contents;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;
import nl.changer.audiowife.AudioWife;

public class SongPickerActivity extends BaseActivity {

    private static final String TAG = "SongPickerActivity";

    private final List<Disposable> mDisposables = new ArrayList<>();
    ActivitySongPickerBinding binding;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==SharedConstants.REQUEST_CODE_PICK_SONG_FILE && resultCode==RESULT_OK && data!=null) {
            try {
                closeWithSelection(null, copySongFile(data.getData()));
            } catch (Exception e) {
                Log.e(TAG, "Failed to copy song file on phone.");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    SongsAdapter songsAdapter = new SongsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_song_picker);


        List<Song> songs = Demo_contents.getSongFiles();
        songsAdapter.addData(songs);
        songsAdapter.setOnSongClickListner(song -> downloadSelectedSong(song));
        binding.rvSongs.setAdapter(songsAdapter);
        initView();
    }

    private void initView() {
        View browse = findViewById(R.id.browse);
        browse.setOnClickListener(v ->
                IntentUtil.startChooser(
                        this, SharedConstants.REQUEST_CODE_PICK_SONG_FILE, "audio/*"));
        View sheet = findViewById(R.id.song_preview_sheet);
        BottomSheetBehavior<View> bsb = BottomSheetBehavior.from(sheet);
        bsb.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View sheet, int state) {
                Log.v(TAG, "Song preview sheet state is: " + state);
                if (state==BottomSheetBehavior.STATE_COLLAPSED) {
                    AudioWife.getInstance().release();
                }
            }

            @Override
            public void onSlide(@NonNull View sheet, float offset) {
                Log.d(TAG, "onSlide: ");
            }


        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioWife.getInstance().release();
        for (Disposable disposable : mDisposables) {
            disposable.dispose();
        }

        mDisposables.clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
        AudioWife.getInstance().pause();
    }

    public void downloadSelectedSong(final Song song) {
        File songs = new File(getFilesDir(), "songs");
        if (!songs.exists() && !songs.mkdirs()) {
            Log.w(TAG, "Could not create directory at " + songs);
        }

        Log.d(TAG, "downloadSelectedSong: " + song.audio);
        String extension = song.audio.substring(song.audio.lastIndexOf(".") + 1);
        File audio = new File(songs, song.id + extension);
        if (audio.exists()) {
            playSelection(song, Uri.fromFile(audio));
            return;
        }

        KProgressHUD progress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getString(R.string.progress_title))
                .setCancellable(false)
                .show();
        Data input = new Data.Builder()
                .putString(FileDownloadWorker.KEY_INPUT, song.audio)
                .putString(FileDownloadWorker.KEY_OUTPUT, audio.getAbsolutePath())
                .build();
        WorkRequest request = new OneTimeWorkRequest.Builder(FileDownloadWorker.class)
                .setInputData(input)
                .build();
        WorkManager wm = WorkManager.getInstance(this);
        wm.enqueue(request);
        wm.getWorkInfoByIdLiveData(request.getId())
                .observe(this, info -> {
                    boolean ended = info.getState()==WorkInfo.State.CANCELLED
                            || info.getState()==WorkInfo.State.FAILED
                            || info.getState()==WorkInfo.State.SUCCEEDED;
                    if (ended) {
                        progress.dismiss();
                    }

                    if (info.getState()==WorkInfo.State.SUCCEEDED) {
                        playSelection(song, Uri.fromFile(audio));
                    }
                });
    }

    private void closeWithSelection(@Nullable Song song, Uri file) {
        Intent data = new Intent();
        if (song!=null) {

        }


        setResult(RESULT_OK, data);
        finish();
    }

    private Uri copySongFile(Uri uri) throws Exception {
        InputStream is = getContentResolver().openInputStream(uri);
        File target = TempUtil.createNewFile(this, "audio");
        OutputStream os = new FileOutputStream(target);
        IOUtils.copy(is, os);
        is.close();
        os.close();
        return Uri.fromFile(target);
    }

    private void playSelection(Song song, Uri file) {
        View sheet = findViewById(R.id.song_preview_sheet);
        AudioWife.getInstance().release();
        AudioWife.getInstance()
                .init(this, file)
                .setPlayView(sheet.findViewById(R.id.play))
                .setPauseView(sheet.findViewById(R.id.pause))
                .setSeekBar(sheet.findViewById(R.id.seekbar))
                .setRuntimeView(sheet.findViewById(R.id.start))
                // .setTotalTimeView(sheet.findViewById(R.id.end))
                .play();
        TextView song2 = sheet.findViewById(R.id.song);
        song2.setText(song.title);
        sheet.findViewById(R.id.use)
                .setOnClickListener(v -> closeWithSelection(song, file));
        BottomSheetBehavior<View> bsb = BottomSheetBehavior.from(sheet);
        bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


}
