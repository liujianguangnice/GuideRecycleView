package com.ljg.guiderecycleview.customview.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.ljg.guiderecycleview.R;


/**
 * @author ：ljg on 2018/10/24 17：38
 * @describe
 * @email：liujga@enn.cn
 */
public class VideoOfflineDialog extends CommonDialog {

    private TextView contentView;

    public VideoOfflineDialog(@NonNull Context context) {
        super(context);
    }

    public VideoOfflineDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }


    public VideoOfflineDialog(Context context, boolean isCancelable, boolean isBackCancelable) {
        super(context, isCancelable, isBackCancelable);
    }

    @Override
    public View setDialogLayout() {
        View inflate = View.inflate(getContext(), R.layout.dialog_base_video_offline, null);
        contentView = inflate.findViewById(R.id.tv_content);
        inflate.findViewById(R.id.ll_root_dialog).setOnClickListener(v -> {
            if (mOnSettingListener != null) {
                mOnSettingListener.onSure();
            }
        });
        return inflate;
    }

    public void setContentView(String text) {
        contentView.setText(text);
    }

    public TextView getContentView() {
        return contentView;
    }


    private OnSettingListener mOnSettingListener;

    public void setOnSettingListener(OnSettingListener onSettingListener) {
        this.mOnSettingListener = onSettingListener;
    }

    public interface OnSettingListener {
        void onSure();
    }

}
