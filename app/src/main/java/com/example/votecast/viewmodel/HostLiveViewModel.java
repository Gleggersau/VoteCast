package com.example.votecast.viewmodel;

import android.util.Log;

import androidx.camera.core.CameraX;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.VideoCaptureConfig;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.votecast.livestreamming.FilterAdapter;
import com.example.votecast.livestreamming.FilterAdapter2;
import com.example.votecast.livestreamming.HostLiveActivity;
import com.example.votecast.livestreamming.LiveStramCommentAdapter;
import com.example.votecast.livestreamming.LiveViewUserAdapter;
import com.example.votecast.livestreamming.StickerAdapter;
import com.example.votecast.models.Sticker;
import com.example.votecast.models.User;
import com.example.votecast.utils.filters.FilterRoot;

public class HostLiveViewModel extends ViewModel {
    public CameraX.LensFacing lensFacing = CameraX.LensFacing.FRONT;

    public FilterAdapter filterAdapter_tt = new FilterAdapter();
    public FilterAdapter2 filterAdapter2 = new FilterAdapter2();
    public StickerAdapter stickerAdapter = new StickerAdapter();

    public LiveViewUserAdapter liveViewUserAdapter = new LiveViewUserAdapter();
    public LiveStramCommentAdapter liveStramCommentAdapter = new LiveStramCommentAdapter();
    public PreviewConfig.Builder builder;
    public PreviewConfig previewConfig;
    public Preview preview;
    public VideoCaptureConfig.Builder builder1;
    public VideoCaptureConfig videoCaptureConfig;
    public VideoCapture videoCapture;
    public MutableLiveData<Boolean> isShowFilterSheet = new MutableLiveData<>(false);
    public MutableLiveData<FilterRoot> selectedFilter = new MutableLiveData<>();
    public MutableLiveData<FilterRoot> selectedFilter2 = new MutableLiveData<>();
    public MutableLiveData<Sticker> selectedSticker = new MutableLiveData<>();


    public MutableLiveData<User> clickedComment = new MutableLiveData<>();
    public MutableLiveData<User> clickedUser = new MutableLiveData<>();


    public void onClickSheetClose() {
        isShowFilterSheet.setValue(false);
    }

    public void initLister() {
        filterAdapter_tt.setOnFilterClickListnear(new FilterAdapter.OnFilterClickListnear() {
            @Override
            public void onFilterClick(FilterRoot filterRoot) {
                Log.d(HostLiveActivity.TAG + " viewmodel", "onBindViewHolder: ===========" + filterRoot.getTitle());
                selectedFilter.setValue(filterRoot);
            }
        });
        filterAdapter2.setOnFilterClickListnear(new FilterAdapter2.OnFilterClickListnear() {
            @Override
            public void onFilterClick(FilterRoot filterRoot) {
                Log.d(HostLiveActivity.TAG + " viewmodel", "onBindViewHolder: ===========" + filterRoot.getTitle());
                selectedFilter2.setValue(filterRoot);
            }
        });
        stickerAdapter.setOnStickerClickListner(new StickerAdapter.OnStickerClickListner() {
            @Override
            public void onStickerClick(Sticker filterRoot) {
                Log.d(HostLiveActivity.TAG + " viewmodel", "onBindViewHolder: ===========" + filterRoot.image);
                selectedSticker.setValue(filterRoot);
            }


        });
        liveStramCommentAdapter.setOnCommentClickListner((User user) -> clickedComment.setValue(user));
        liveViewUserAdapter.setOnLiveUserAdapterClickLisnter((User user) -> clickedUser.setValue(user));
    }
}
