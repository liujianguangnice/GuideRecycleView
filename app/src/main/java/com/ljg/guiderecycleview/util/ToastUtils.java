package com.ljg.guiderecycleview.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

public class ToastUtils{

    @SuppressLint("StaticFieldLeak")
    private static Context context = AppContextUtils.getContext();

    private static Toast toast;

    /**
     * Toast内容，支持String、资源ID（int）、CharSequence
     *
     * @param msg String、CharSequence
     */
    public static void show(@NonNull String msg) {
        showView(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 对toast的封装。线程安全，可以在非UI线程调用。
     * Toast内容，支持String、资源ID（int）、CharSequence
     *
     * @param resId int、CharSequence
     */
    public static void show(int resId) {
        showView(context.getString(resId), Toast.LENGTH_SHORT);
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     * Toast内容，支持String、资源ID（int）、CharSequence
     *
     * @param msg      String、int、CharSequence
     * @param duration Toast时间。eg:Toast.LENGTH_SHORT
     */
    public static void show(@NonNull String msg, int duration) {
        showView(msg, duration);
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     * Toast内容，支持String、资源ID（int）、CharSequence
     *
     * @param resId    String、int、CharSequence
     * @param duration Toast时间。eg:Toast.LENGTH_SHORT
     */
    public static void show(int resId, int duration) {
        showView(context.getString(resId), duration);
    }


    @SuppressLint("ShowToast")
    private static void showView(CharSequence text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
        }
        toast.show();
    }


    /**
     * 取消Toast
     */
    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

}

