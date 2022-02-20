package com.example.votecast.reels.record.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

import com.google.common.util.concurrent.ListenableFuture;
import com.otaliastudios.transcoder.Transcoder;
import com.otaliastudios.transcoder.TranscoderListener;
import com.otaliastudios.transcoder.TranscoderOptions;
import com.otaliastudios.transcoder.strategy.DefaultVideoStrategy;
import com.otaliastudios.transcoder.strategy.size.PassThroughResizer;

import java.io.File;


public class MergeVideosWorker2 extends ListenableWorker {

    public static final String KEY_INPUTS = "inputs";
    public static final String KEY_OUTPUT = "output";
    private static final String TAG = "MergeVideosWorker2";

    public MergeVideosWorker2(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    @SuppressWarnings("ConstantConditions")
    public ListenableFuture<Result> startWork() {
        String[] paths = getInputData().getStringArray(KEY_INPUTS);
        File output = new File(getInputData().getString(KEY_OUTPUT));
        File[] files = new File[paths.length];
        for (int i = 0; i < paths.length; i++) {
            files[i] = new File(paths[i]);
        }

        return CallbackToFutureAdapter.getFuture(completer -> {
            doActualWork(files, output, completer);
            return null;
        });
    }

    private void doActualWork(File[] inputs, File output, CallbackToFutureAdapter.Completer<Result> completer) {
        TranscoderOptions.Builder transcoder = Transcoder.into(output.getAbsolutePath());


        transcoder.setListener(new TranscoderListener() {

            @Override
            public void onTranscodeProgress(double progress) {
                Log.d(TAG, "onTranscodeProgress: ");
            }

            @Override
            public void onTranscodeCompleted(int code) {
                Log.d(TAG, "Merging video files has finished.");
                completer.set(Result.success());
                for (File input : inputs) {
                    if (!input.delete()) {
                        Log.w(TAG, "Could not delete input file: " + input);
                    }
                }
            }

            @Override
            public void onTranscodeCanceled() {
                Log.d(TAG, "Merging video files was cancelled.");
                completer.setCancelled();
                if (!output.delete()) {
                    Log.w(TAG, "Could not delete failed output file: " + output);
                }
            }

            @Override
            public void onTranscodeFailed(@NonNull Throwable e) {
                Log.d(TAG, "Merging video files failed with error.", e);
                completer.setException(e);
                if (!output.delete()) {
                    Log.w(TAG, "Could not delete failed output file: " + output);
                }
            }
        });
        transcoder.setVideoTrackStrategy(
                new DefaultVideoStrategy.Builder(new PassThroughResizer()).build());
        transcoder.transcode();
    }
}
