package com.example.votecast.dilog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.databinding.DailogLoaderBinding;
import com.example.votecast.databinding.LoutPopupBinding;


public class CustomDialogBuilder {
    private Context mContext;
    private Dialog mBuilder = null;

    public CustomDialogBuilder(Context context) {
        this.mContext = context;
        if (mContext != null) {
            mBuilder = new Dialog(mContext);
            mBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mBuilder.setCancelable(false);
            mBuilder.setCanceledOnTouchOutside(false);
            if (mBuilder.getWindow()!=null) {
                mBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
    }

    public void showSimpleDialog(String title, String message, String negativeText, String positiveText, OnDismissListener onDismissListener) {

        if (mContext == null)
            return;

        mBuilder.setCancelable(true);
        mBuilder.setCanceledOnTouchOutside(true);
        LoutPopupBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.lout_popup, null, false);
        mBuilder.setContentView(binding.getRoot());
        Dialog1 dialog1 = new Dialog1();
        dialog1.setTitle(title);
        dialog1.setMessage(message);
        dialog1.setPositiveText(positiveText);
        dialog1.setNegativeText(negativeText);
        binding.setModel(dialog1);
        binding.tvPositive.setOnClickListener(v -> {
            mBuilder.dismiss();
            onDismissListener.onPositiveDismiss();
        });
        binding.tvCancel.setOnClickListener(v -> {
            mBuilder.dismiss();
            onDismissListener.onNegativeDismiss();
        });
        mBuilder.show();

    }


    public void showLoadingDialog() {
        if (mContext == null)
            return;

        mBuilder.setCancelable(false);
        mBuilder.setCanceledOnTouchOutside(false);
        DailogLoaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dailog_loader, null, false);
        Animation rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
        Animation reverseAnimation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_reverse);
        binding.ivParent.startAnimation(rotateAnimation);
        binding.ivChild.startAnimation(reverseAnimation);
        mBuilder.setContentView(binding.getRoot());
        mBuilder.show();
    }

    public void hideLoadingDialog() {
        try {
            mBuilder.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnResultButtonClick {
        void onButtonClick(boolean success);
    }

    public interface OnDismissListener {
        void onPositiveDismiss();

        void onNegativeDismiss();
    }

    public interface OnCoinDismissListener {
        void onCancelDismiss();

        void on5Dismiss();

        void on10Dismiss();

        void on20Dismiss();

    }

}