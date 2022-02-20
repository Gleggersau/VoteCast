package com.example.votecast.reels.record.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;


public class MergeAudioVideoWorker2 extends ListenableWorker {

    public static final String KEY_AUDIO = "audio";
    public static final String KEY_OUTPUT = "output";
    public static final String KEY_VIDEO = "video";
    private static final String TAG = "MergeAudioVideoWorker2";

    public MergeAudioVideoWorker2(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    @SuppressWarnings("ConstantConditions")
    public ListenableFuture<Result> startWork() {
        String audio = getInputData().getString(KEY_AUDIO);
        File video = new File(getInputData().getString(KEY_VIDEO));
        File output = new File(getInputData().getString(KEY_OUTPUT));
        return CallbackToFutureAdapter.getFuture(completer -> {
            doActualWork(video, audio, output, completer);
            return null;
        });
    }

    private void doActualWork(File video, String audio, File output, CallbackToFutureAdapter.Completer<Result> completer) {
        Log.d(TAG, "doActualWork: ");


    }
}
