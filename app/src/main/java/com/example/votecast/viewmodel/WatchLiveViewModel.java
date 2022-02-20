package com.example.votecast.viewmodel;

import androidx.camera.core.CameraX;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.VideoCaptureConfig;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.votecast.livestreamming.LiveStramCommentAdapter;
import com.example.votecast.livestreamming.LiveViewUserAdapter;
import com.example.votecast.models.LiveCommentRoot;
import com.example.votecast.models.User;

public class WatchLiveViewModel extends ViewModel {
    public CameraX.LensFacing lensFacing = CameraX.LensFacing.FRONT;
    public PreviewConfig.Builder builder;
    public PreviewConfig previewConfig;
    public Preview preview;
    public VideoCaptureConfig.Builder builder1;
    public VideoCaptureConfig videoCaptureConfig;
    public VideoCapture videoCapture;


    public LiveViewUserAdapter liveViewUserAdapter = new LiveViewUserAdapter();
    public LiveStramCommentAdapter liveStramCommentAdapter = new LiveStramCommentAdapter();
    public MutableLiveData<LiveCommentRoot> clickedComment = new MutableLiveData<>();
    public MutableLiveData<User> clickedUser = new MutableLiveData<>();

    public void initLister() {
        liveStramCommentAdapter.setOnCommentClickListner((User user) -> {
            clickedUser.setValue(user);
        });
        liveViewUserAdapter.setOnLiveUserAdapterClickLisnter((User user) -> clickedUser.setValue(user));

    }

}
