package com.ljg.guiderecycleview.customview.pullrefresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class PullLinearLayout extends LinearLayout implements Pullable, OnTitleShowListener {

  private boolean isTop;

  public PullLinearLayout(Context context) {
    super(context);
  }

  public PullLinearLayout(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public PullLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
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
