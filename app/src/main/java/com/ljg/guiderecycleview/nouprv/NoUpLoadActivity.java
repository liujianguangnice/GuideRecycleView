package com.ljg.guiderecycleview.nouprv;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ljg.guiderecycleview.R;
import com.ljg.guiderecycleview.R2;
import com.ljg.guiderecycleview.customview.GridSpacingItemDecoration;
import com.ljg.guiderecycleview.customview.dialog.VideoOfflineDialog;
import com.ljg.guiderecycleview.customview.pullrefresh.PullToRefreshLayout;
import com.ljg.guiderecycleview.baseui.BaseActivity;
import com.ljg.guiderecycleview.util.GoPageUtil;
import com.ljg.guiderecycleview.util.LogUtils;
import com.ljg.guiderecycleview.util.ToastUtils;
import com.ljg.guiderecycleview.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NoUpLoadActivity extends BaseActivity {
    private final String TAG = this.getClass().getSimpleName();

    @BindView(R2.id.refreshView_video)
    PullToRefreshLayout reFreshViewVideo;
    @BindView(R2.id.lv_content)
    RecyclerView rvVideoControl;

    private List<VideoControlBean.DataBean> mData = new ArrayList<>();

    private VideoControlAdapter mVideoControlAdapter;


    private VideoOfflineDialog mOfflineDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_no_up_load;
    }


    private void initView() {
        reFreshViewVideo.setOnPullListener(new MyPullRefresh());
        reFreshViewVideo.setPullUpEnable(false);

        mVideoControlAdapter = new VideoControlAdapter(this);
        rvVideoControl.setAdapter(mVideoControlAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rvVideoControl.setLayoutManager(gridLayoutManager);
        rvVideoControl.addItemDecoration(new GridSpacingItemDecoration(2, UiUtils.dp2px(getApplication(), 16), false));
        rvVideoControl.setItemAnimator(new DefaultItemAnimator());

        mVideoControlAdapter.setOnViewItemClickListener(new VideoControlAdapter.IBaseItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Boolean isSelect) {
                VideoControlBean.DataBean beanTmp = mData.get(position);
                ToastUtils.show("showOfflineDialog1");
                if (0 == beanTmp.getStatus()) {
                    ToastUtils.show("showOfflineDialog2");
                    showOfflineDialog();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("deviceId", beanTmp.getDeviceId());
                    bundle.putString("deviceName", beanTmp.getDeviceName());
                    bundle.putString("stationName", beanTmp.getStationName());
                    bundle.putString("stationId", beanTmp.getStationId());
                    bundle.putInt("channelNo", beanTmp.getChannelNo());
                    bundle.putString("accessToken", beanTmp.getAccessToken());
                    GoPageUtil.goPage(getBaseContext(), PlayActivity.class, bundle);
                }
            }

            @Override
            public void onItemInnerViewClick(View view, int postion, Boolean isSelect) {

            }

            @Override
            public void onErrorClick() {
                refreshDeviceInfoList(false);
            }
        });

    }




    private void showOfflineDialog() {
        if (mOfflineDialog == null) {
            mOfflineDialog = new VideoOfflineDialog(this, R.style.MyDialog);
            mOfflineDialog.setOnSettingListener(new VideoOfflineDialog.OnSettingListener() {
                @Override
                public void onSure() {
                    mOfflineDialog.dismiss();
                }
            });
        }
        mOfflineDialog.show();

    }

    private class MyPullRefresh implements PullToRefreshLayout.OnPullListener {
        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            LogUtils.i(TAG, "onRefresh: ");
            showData(null);
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }


        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            LogUtils.i(TAG, "onLoadMore: ");
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }
    }

    private void refreshDeviceInfoList(boolean b) {
        showEmptyView();

    }


    public <E> void showData(E data) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvVideoControl.setLayoutManager(gridLayoutManager);
        mData.clear();
       // mData = (List<VideoControlBean.DataBean>) data;

        VideoControlBean.DataBean ban2;
        for (int i = 0; i <10; i++) {
            ban2 = new VideoControlBean.DataBean() ;
            ban2.setStationName("ceshi"+i);
            ban2.setDeviceName("ceshi"+i);
            ban2.setStatus(0);
            ban2.setPicUrl(null);
            mData.add(ban2);
        }

        mVideoControlAdapter.setmDatas(mData);
    }

    public void showEmptyView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rvVideoControl.setLayoutManager(gridLayoutManager);
        mVideoControlAdapter.setEmptyView();
    }

    //无网络状态空View
    public void showErrorView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rvVideoControl.setLayoutManager(gridLayoutManager);
        mVideoControlAdapter.setErrorView();
    }


}
