package com.example.votecast.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

final public class TempUtil {

    private static final long CLEANUP_CUTOFF =
            System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3);

    private static final String TAG = "TempUtil";

    public static void cleanupStaleFiles(Context context) {
        IOFileFilter filter = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.ageFileFilter(CLEANUP_CUTOFF),
                FileFilterUtils.or(
                        FileFilterUtils.suffixFileFilter(".gif", IOCase.INSENSITIVE),
                        FileFilterUtils.suffixFileFilter(".png", IOCase.INSENSITIVE),
                        FileFilterUtils.suffixFileFilter(".mp4", IOCase.INSENSITIVE)
                )
        );
        Collection<File> stale =
                FileUtils.listFiles(context.getCacheDir(), filter, null);
        if (!stale.isEmpty()) {
            for (File file : stale) {
                FileUtils.deleteQuietly(file);
            }
        }
    }

    public static File createCopy(Context context, Uri uri, @Nullable String suffix) {
        File temp = createNewFile(context, suffix);
        try (InputStream is = context.getContentResolver().openInputStream(uri);
             OutputStream os = new FileOutputStream(temp)) {
            IOUtils.copy(is, os);
        } catch (Exception e) {
            Log.e(TAG, "Could not copy " + uri);
        }

        return temp;
    }

    public static File createNewFile(Context context, @Nullable String suffix) {
        return createNewFile(context.getCacheDir(), suffix);
    }

    public static File createNewFile(File directory, @Nullable String suffix) {
        return new File(directory, UUID.randomUUID().toString() + suffix);
    }
}
