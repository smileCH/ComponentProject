package com.smile.ch.common.utils;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.smile.ch.common.R;
import com.smile.ch.common.http.cancle.ApiCancleManager;

/**
 * Author：CHENHAO
 * date：2018/5/3
 * desc：
 */

public class CustomProgressDialog extends Dialog {
    private Context context;
    private static CustomProgressDialog dialog;

    public CustomProgressDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CustomProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    public static CustomProgressDialog createDialog(Context context, final boolean canCancle){
        dialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        dialog.setContentView(R.layout.progress_dialog_layout);

        ImageView iv_loading_logo = (ImageView) dialog.findViewById(R.id.iv_loading_logo);
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_loading_logo, "rotation", 0F, 360F);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(1000);
        animator.setDuration(2000).start();

        dialog.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (canCancle) {
                        ApiCancleManager.getInstance().cancelAll();
                        return false;
                    }else {
                        return true;
                    }
                }
                return false;
            }
        });

        return dialog;
    }


    /**
     * 创建进度条实例
     */
    public void createProgressDialog(Context cxt, boolean canCancle) {
        try {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
            if (dialog == null) {
                dialog = createDialog(cxt, canCancle);
                dialog.setCanceledOnTouchOutside(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动加载进度条
     */
    public void showProgressDialog(){
        try {
            if (dialog != null) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭加载进度条
     */
    public void stopProgressDialog() {
        try {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
