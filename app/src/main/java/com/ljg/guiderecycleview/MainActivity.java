package com.ljg.guiderecycleview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.ljg.guiderecycleview.baseui.BaseActivity;
import com.ljg.guiderecycleview.labellistviewrv.EnthalpyQueryActivity;
import com.ljg.guiderecycleview.nouprv.NoUpLoadActivity;
import com.ljg.guiderecycleview.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final int MSG_SELECT_ENTHALPY_CLASS = 0x2220;
    private static final int DISAPPEAR_TEXT = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            int message = msg.what;
            if (message == DISAPPEAR_TEXT) {
                ToastUtils.show("1.5s DISAPPEAR_TEXT"+id);
            }
        }
    };
    private String id;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode){
            case  MSG_SELECT_ENTHALPY_CLASS:
                if (data != null) {
                     id = data.getStringExtra("id");
                    //finish();
                    mHandler.sendEmptyMessageDelayed(DISAPPEAR_TEXT, 1500);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler == null) {
            mHandler.removeMessages(DISAPPEAR_TEXT);
        }
    }


    @OnClick({R.id.bt_1,R.id.bt_2})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.bt_1) {
            Intent intent = new Intent(this, NoUpLoadActivity.class);
            startActivity(intent);
        }else if(id == R.id.bt_2){
            Intent intent = new Intent(this, EnthalpyQueryActivity.class);
            intent.putExtra("model", "model");
            intent.putExtra("modelName", "modelName");
            intent.putExtra("data_temperature", "data_temperature");
            intent.putExtra("data_press", "data_press");
            startActivityForResult(intent, MSG_SELECT_ENTHALPY_CLASS);
        }


    }


}
