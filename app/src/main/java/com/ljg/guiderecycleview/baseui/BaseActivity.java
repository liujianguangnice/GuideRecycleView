package com.ljg.guiderecycleview.baseui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.ljg.guiderecycleview.util.StatusBarUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends CommonActivity {

    private BaseActivity thisActivity = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(this, initLayout(), null);
        setContentView(view);
        //沉浸式状态栏
        StatusBarUtils.translucent(this);
        ButterKnife.bind(this);
        thisActivity = this;
    }


    /**
     * @return 初始化布局
     */
    protected abstract @LayoutRes
    int initLayout();
}


