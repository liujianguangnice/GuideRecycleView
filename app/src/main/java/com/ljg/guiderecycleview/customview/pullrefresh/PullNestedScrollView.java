package com.ljg.guiderecycleview.customview.pullrefresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * @author ：mp5a5 on 2018/9/28 10：06
 * @describe
 * @email：wwb199055@126.com
 */
public class PullNestedScrollView extends NestedScrollView implements Pullable {

  public PullNestedScrollView(@NonNull Context context) {
    super(context);
  }

  public PullNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public PullNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean canPullDown() {
    return getScrollY() == 0;
  }

  @Override
  public boolean canPullUp() {
    return true;
  }


}
