package com.ljg.guiderecycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ljg.guiderecycleview.nouprv.NoUpLoadActivity;
import com.ljg.guiderecycleview.util.AppContextUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //必须在加载布局（setContentView）之后
        ButterKnife.bind(this);

        AppContextUtils.init(this);
    }

    @OnClick(R.id.bt_1)
    public void onViewClicked(View view) {
        int id = view.getId();
        if(id==R.id.bt_1){
            Intent intent = new Intent(this,NoUpLoadActivity.class);
            startActivity(intent);
        }
    }
}
