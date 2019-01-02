package com.ljg.guiderecycleview.customview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.ljg.guiderecycleview.R;


/**
 * 作者：王文彬 on 2018/3/29 13：37
 * 邮箱：wwb199055@126.com
 */

public abstract class CommonDialog extends Dialog {

  private boolean mIsBackCancelable = true;
  private Context mContext;
  private boolean mIscancelable = true;

  public CommonDialog(@NonNull Context context) {
    super(context);
    this.mContext = context;
    initView();
  }


  /**
   * 自定义布局及主题的构造方法
   *
   * @param context 上下文
   * @param theme   主题
   */
  public CommonDialog(@NonNull Context context, int theme) {
    super(context, theme);
    this.mContext = context;
    initView();
  }
  public CommonDialog(@NonNull Context context, int theme, Boolean isBackCancelable) {
    super(context, theme);
    this.mContext = context;
    this.mIsBackCancelable = isBackCancelable;
    initView();
  }

  public CommonDialog(Context context, boolean isCancelable, boolean isBackCancelable) {
    super(context, R.style.MyDialog);
    this.mContext = context;
    this.mIscancelable = isCancelable;
    this.mIsBackCancelable = isBackCancelable;
    initView();
  }

  private void initView() {
    View view = View.inflate(mContext, R.layout.dialog_common_view, null);
    RelativeLayout containerRl = (RelativeLayout) view.findViewById(R.id.rl_container);
    View mDialogLayout = setDialogLayout();
    containerRl.addView(mDialogLayout);
    setContentView(view);
    setCancelable(mIscancelable);
    setCanceledOnTouchOutside(mIsBackCancelable);
  }

  public abstract View setDialogLayout();

}
