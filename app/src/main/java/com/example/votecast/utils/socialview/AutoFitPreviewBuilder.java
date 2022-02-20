package com.example.votecast.utils.socialview;

import android.util.Size;
import android.view.Display;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;

import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;

import java.lang.ref.WeakReference;

public class AutoFitPreviewBuilder {
    Preview previewUseCase;
    int bufferRotation = 0;
    int viewFinderRotation = 0;
    Size bufferDimens = new Size(0, 0);
    Size viewFinderDimens = new Size(0, 0);
    PreviewConfig config;
    WeakReference<TextureView> viewFinderRef;

    public AutoFitPreviewBuilder(PreviewConfig config, WeakReference<TextureView> viewFinderRef) {
        this.config = config;
        this.viewFinderRef = viewFinderRef;

        TextureView viewFinder = viewFinderRef.get();
        viewFinderRotation = getDisplaySurfaceRotation(viewFinder.getDisplay());

        // Initialize public use-case with the given config
        previewUseCase = new Preview(config);
        previewUseCase.setOnPreviewOutputUpdateListener(output -> {
            ViewGroup parent = (ViewGroup) viewFinder.getParent();
            parent.removeView(viewFinder);
            parent.addView(viewFinder, 0);
            if (viewFinder.getSurfaceTexture()!=null) {
                viewFinder.setSurfaceTexture(output.getSurfaceTexture());
            }

        });


    }

    private int getDisplaySurfaceRotation(Display display) {
        switch (display.getRotation()) {
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;

            default:

        }
        return 0;
    }
}
