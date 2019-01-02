package com.ljg.guiderecycleview.nouprv;

import android.os.Bundle;
import android.widget.Toast;

import com.ljg.guiderecycleview.R;
import com.ljg.guiderecycleview.baseui.BaseActivity;

public class PlayActivity extends BaseActivity {

    private String deviceId;
    private int channelNo;
    private String stationName;
    private String stationId;
    private String deviceName;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentInfo();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_play;
    }

    /**
     * 获取上一页面的数据
     */
    private void getIntentInfo() {
        Bundle bundle = getIntent().getExtras();
        deviceId = bundle.getString("deviceId");
        channelNo = bundle.getInt("channelNo");
        stationName = bundle.getString("stationName");
        stationId = bundle.getString("stationId");
        deviceName = bundle.getString("deviceName");
        accessToken = bundle.getString("accessToken");

        Toast.makeText(this,deviceId,Toast.LENGTH_SHORT).show();

    }


}
