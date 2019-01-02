package com.ljg.guiderecycleview.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class CommonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


      /*  EventBus.getDefault().registerSticky(this);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     /*   if (!EventBus.getDefault().getStickyEvents().isEmpty()) {
            EventBus.getDefault().getStickyEvents().clear();
        }
        EventBus.getDefault().unregister(this);*/
    }
}
