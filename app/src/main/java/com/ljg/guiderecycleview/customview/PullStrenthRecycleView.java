package com.ljg.guiderecycleview.customview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.ljg.guiderecycleview.customview.pullrefresh.OnTitleShowListener;
import com.ljg.guiderecycleview.customview.pullrefresh.Pullable;


public class PullStrenthRecycleView extends RecyclerView implements Pullable, OnTitleShowListener {
    private boolean isTop;


    public PullStrenthRecycleView(Context context) {
        super(context);
    }

    public PullStrenthRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
