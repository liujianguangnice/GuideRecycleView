package com.ljg.guiderecycleview.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作者： liujianguang on 2018/10/9 15:48
 * 邮箱： liujga@enn.cn
 */
public class StrenthListView extends ListView {
    public StrenthListView(Context context) {
        super(context);
    }


    public StrenthListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
