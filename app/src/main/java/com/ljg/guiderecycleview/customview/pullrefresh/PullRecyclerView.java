package com.ljg.guiderecycleview.customview.pullrefresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public class PullRecyclerView extends RecyclerView implements Pullable, OnTitleShowListener {

    private boolean isTop;

    public PullRecyclerView(Context context) {
        super(context);
    }

    public PullRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public OnTitleShowListener getOnTitleShowListener() {
        return this;
    }

    @Override
    public boolean canPullDown() {
        if (!isTop) {
            if (getChildCount() == 0) {
                // 没有item的时候也可以下拉刷新,findFirstVisibleItemPosition
                return true;
            } else // 滑到ListView的顶部了
                return !this.canScrollVertically(-1);
        } else {
            return false;
        }
    }

    @Override
    public void showTitle(boolean isTop) {
        this.isTop = isTop;
    }

    @Override
    public boolean canPullUp() {
        if (getChildCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        } else return !this.canScrollVertically(1);
    }
}
