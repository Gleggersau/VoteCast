package com.example.votecast.posts;


import static android.provider.MediaStore.MediaColumns.DATA;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.votecast.R;
import com.example.votecast.activity.BaseActivity;
import com.example.votecast.databinding.ActivityUploadPostBinding;
import com.example.votecast.databinding.BottomSheetPrivacyBinding;
import com.example.votecast.retrofit.Const;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

public class UploadPostActivity extends BaseActivity {
    private static final int GALLERY_CODE = 101;
    private static final int PERMISSION_REQUEST_CODE = 1001;
    public static final int REQ_CODE_HASHTAG = 122;
    public static final int REQ_CODE_LOCATION = 123;
    private static final String TAG = "uploadpost";
    ActivityUploadPostBinding binding;
    private Privacy privacy = Privacy.PUBLIC;
    private Uri selectedImage;
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload_post);
        setPrivacy(privacy);
        initListner();

    }

    private void initListner() {
        binding.lytPrivacy.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.customStyle);
            BottomSheetPrivacyBinding sheetPrivacyBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.bottom_sheet_privacy, null, false);
            bottomSheetDialog.setContentView(sheetPrivacyBinding.getRoot());
            bottomSheetDialog.show();
            sheetPrivacyBinding.tvPublic.setOnClickListener(v1 -> {
                setPrivacy(Privacy.PUBLIC);
                bottomSheetDialog.dismiss();
            });
            sheetPrivacyBinding.tvOnlyFollowr.setOnClickListener(v1 -> {
                setPrivacy(Privacy.FOLLOWRS);
                bottomSheetDialog.dismiss();
            });
            sheetPrivacyBinding.tvOnlyMe.setOnClickListener(v1 -> {
                setPrivacy(Privacy.PRIVATE);
                bottomSheetDialog.dismiss();
            });

        });
        binding.btnAdd.setOnClickListener(v -> choosePhoto());
        binding.btnDelete.setOnClickListener(v -> {
            binding.imageview.setImageDrawable(null);
            selectedImage = null;
            picturePath = "";
            binding.btnDelete.setVisibility(View.GONE);
            binding.btnAdd.setVisibility(View.VISIBLE);
        });


    }

    private void choosePhoto() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("value", "Permission Granted, Now you can use local drive .");
                choosePhoto();
            } else {
                Log.e("value", "Permission Denied, You cannot use local drive .");
            }
        }
    }

    private void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
        if (privacy==Privacy.PRIVATE) {
            binding.tvPrivacy.setText("Only Me");
        } else if (privacy==Privacy.FOLLOWRS) {
            binding.tvPrivacy.setText("My Followers");
        } else {
            binding.tvPrivacy.setText("Public");
        }
    }

    public void onClickPost(View view) {
        Toast.makeText(this, "Uploading...", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    enum Privacy {
        PUBLIC, FOLLOWRS, PRIVATE
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLERY_CODE && resultCode==RESULT_OK && null!=data) {

            selectedImage = data.getData();

            Glide.with(this)
                    .load(selectedImage)
                    .placeholder(R.drawable.ic_user_place).error(R.drawable.ic_user_place)
                    .into(binding.imageview);
            binding.imageview.setAdjustViewBounds(true);

            binding.btnDelete.setVisibility(View.VISIBLE);


        }

        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode==RESULT_OK) {
                selectedImage = result.getUri();
                Log.d(TAG, "onActivityResult:uri " + selectedImage);

                Glide.with(this)
                        .load(selectedImage)
                        .placeholder(R.drawable.ic_user_place).error(R.drawable.ic_user_place)
                        .into(binding.imageview);
                binding.imageview.setAdjustViewBounds(true);

                picturePath = getRealPathFromURI(selectedImage);

                binding.btnDelete.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode==REQ_CODE_HASHTAG && resultCode==RESULT_OK && data!=null) {
            String hashtag = data.getStringExtra(Const.DATA);
            binding.tvHashtag.setText(hashtag);
        }
        if (requestCode==REQ_CODE_LOCATION && resultCode==RESULT_OK && data!=null) {
            String hashtag = data.getStringExtra(Const.DATA);
            binding.tvLocation.setText(hashtag);
        }

    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor==null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}