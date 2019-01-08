package com.ljg.guiderecycleview.labellistviewrv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ljg.guiderecycleview.R;
import com.ljg.guiderecycleview.baseui.BaseActivity;
import com.ljg.guiderecycleview.customview.StretchRecyclerView;
import com.ljg.guiderecycleview.labellistviewrv.entities.ProfessionItemModel;
import com.ljg.guiderecycleview.labellistviewrv.entities.RecycleItemModel;
import com.ljg.guiderecycleview.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * @author : ljg on @time 2018/10/16 17:59
 * @email : liujga@enn.cn
 * @desscribe :
 */
public class EnthalpyQueryActivity extends BaseActivity {



    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecycleView;
    private ProfessionRecycleAdapter mRecycleAdapter;
    private List<RecycleItemModel> mData = new ArrayList<>();


    private String[] titleArray = new String[]{"过热蒸汽区", "饱和状态", "湿热汽区"};
    private String[] titleArrayOther = new String[]{"不饱和水区", "饱和状态", "湿热汽区"};
    private String[] titleArrayLess = new String[]{"饱和状态", "湿热汽区"};
    private String[] innerArrayOne = new String[]{"比容 V ：", "焓 h ：", "熵 s ："};
    private ArrayList innerArrayOneValue = new ArrayList();
    private String[] innerArrayOneUnit = new String[]{"m³/kg", "kJ/kg", "kJ/(kg.k)"};

    private String[] innerArrayTwo = new String[]{"压力 ps ：", "比容 V ′ ：", "焓 h ′ ：", "熵 s ′ ：", "潜热 r ：", "温度 ts ：", "比容 V ′′ ：", "焓 h ′′ ：", "熵 s ′′ ："};
    private ArrayList innerArrayTwoValue = new ArrayList();
    private String[] innerArrayTwoUnit = new String[]{"Mpa", "m³/kg", "kJ/kg", "kJ/(kg.k)", "kJ/kg", "℃", "m³/kg", "kJ/kg", "kJ/(kg.k)"};

    private String[] innerArrayThree = new String[]{"干度 X ：", "比容 V ：", "焓 h ：", "熵 s ："};
    private ArrayList innerArrayThreeValue = new ArrayList();
    private String[] innerArrayThreeUnit = new String[]{"", "m³/kg", "kJ/kg", "kJ/(kg.k)"};
    private String mTemperature;
    private String mPress;

    private String mDefault_value = "--";


    @Override
    protected int initLayout() {
        return R.layout.activity_enthalpy_query;
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = this.getIntent();
        Integer model = intent.getIntExtra("model", 0);
        String modelName = intent.getStringExtra("modelName");
        mTemperature = intent.getStringExtra("data_temperature");
        mPress = intent.getStringExtra("data_press");
        LogUtils.e(model + "," + modelName + "," + mTemperature + "," + mPress + "");

    }

    @Override
    protected void initView() {
        super.initView();

        mRecycleView = findViewById(R.id.recy_recyclerView);
        mLayoutManager = new LinearLayoutManager(getThisActivity(), LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleAdapter = new ProfessionRecycleAdapter(getThisActivity(), mData);
        mRecycleView.setAdapter(mRecycleAdapter);

        mRecycleAdapter.setOnRecycleSubViewAdapterListener(new ProfessionRecycleAdapter.OnRecycleSubViewAdapterListener() {
            @Override
            public void onRecycleItem(int recyclePosition) {
                //ToastUtils.show(""+mData.get(recyclePosition).titleName);
            }

            @Override
            public void onRecycleSubViewClick(int recyclePosition, int gvPosition) {
                //Toast.makeText(RecyMainActivity.this, mData.get(recyclePosition).titleId + "," + mData.get(recyclePosition).modelList.get(gvPosition).id, Toast.LENGTH_SHORT).show();
            }
        });

        initData();
    }


    private void initData() {

        for (int i = 0; i < innerArrayOne.length; i++) {
            innerArrayOneValue.add(mDefault_value);
        }
        for (int i = 0; i < innerArrayTwo.length; i++) {
            innerArrayTwoValue.add(mDefault_value);
        }
        for (int i = 0; i < innerArrayThree.length; i++) {
            innerArrayThreeValue.add(mDefault_value);
        }

        List<RecycleItemModel> empData = initAllUnit(titleArray);
        mRecycleAdapter.updateData(empData);

    }

    private List<RecycleItemModel> initAllUnit(String[] titleArray) {
        List<RecycleItemModel> mData = new ArrayList<>();
        RecycleItemModel mRecycleItemModel;
        for (int i = 0; i < titleArray.length; i++) {
            mRecycleItemModel = new RecycleItemModel();
            mRecycleItemModel.titleId = titleArray[i];
            mRecycleItemModel.titleName = titleArray[i];

            if (i == 0) {
                List<ProfessionItemModel> mProfessionItemModel = new ArrayList<>();
                ProfessionItemModel tmpProfessionItemModel;
                for (int j = 0; j < innerArrayOne.length; j++) {
                    tmpProfessionItemModel = new ProfessionItemModel();
                    tmpProfessionItemModel.name = innerArrayOne[j];
                    tmpProfessionItemModel.value = innerArrayOneValue.get(j).toString();
                    tmpProfessionItemModel.valueUnit = innerArrayOneUnit[j];
                    if (j == innerArrayOne.length - 1) {
                        tmpProfessionItemModel.isHaveDivider = true;
                    }
                    mProfessionItemModel.add(tmpProfessionItemModel);
                }
                mRecycleItemModel.modelList = mProfessionItemModel;
            }

            if (i == 1) {
                List<ProfessionItemModel> mProfessionItemModel = new ArrayList<>();
                ProfessionItemModel tmpProfessionItemModel;
                for (int j = 0; j < innerArrayTwo.length; j++) {
                    tmpProfessionItemModel = new ProfessionItemModel();
                    tmpProfessionItemModel.name = innerArrayTwo[j];
                    tmpProfessionItemModel.value = innerArrayTwoValue.get(j).toString();
                    tmpProfessionItemModel.valueUnit = innerArrayTwoUnit[j];
                    if (j == innerArrayTwo.length - 1) {
                        tmpProfessionItemModel.isHaveDivider = true;
                    }
                    mProfessionItemModel.add(tmpProfessionItemModel);
                }
                mRecycleItemModel.modelList = mProfessionItemModel;
            }

            if (i == 2) {
                List<ProfessionItemModel> mProfessionItemModel = new ArrayList<>();
                ProfessionItemModel tmpProfessionItemModel;
                for (int j = 0; j < innerArrayThree.length; j++) {
                    tmpProfessionItemModel = new ProfessionItemModel();
                    tmpProfessionItemModel.name = innerArrayThree[j];
                    tmpProfessionItemModel.value = innerArrayThreeValue.get(j).toString();
                    tmpProfessionItemModel.valueUnit = innerArrayThreeUnit[j];
                    if (j == innerArrayThree.length - 1) {
                        tmpProfessionItemModel.isHaveDivider = true;
                    }
                    mProfessionItemModel.add(tmpProfessionItemModel);
                }
                mRecycleItemModel.modelList = mProfessionItemModel;
            }

            mData.add(mRecycleItemModel);

        }
        return mData;
    }

    private List<RecycleItemModel> initLessUnit(String[] titleArrayLess) {
        List<RecycleItemModel> mdate = new ArrayList<>();
        RecycleItemModel mRecycleItemModel;
        for (int i = 0; i < titleArrayLess.length; i++) {
            mRecycleItemModel = new RecycleItemModel();
            mRecycleItemModel.titleId = titleArrayLess[i];
            mRecycleItemModel.titleName = titleArrayLess[i];
            if (i == 0) {
                List<ProfessionItemModel> mProfessionItemModel = new ArrayList<>();
                ProfessionItemModel tmpProfessionItemModel;
                for (int j = 0; j < innerArrayTwo.length; j++) {
                    tmpProfessionItemModel = new ProfessionItemModel();
                    tmpProfessionItemModel.name = innerArrayTwo[j];
                    tmpProfessionItemModel.value = innerArrayTwoValue.get(j).toString();
                    tmpProfessionItemModel.valueUnit = innerArrayTwoUnit[j];
                    if (j == innerArrayTwo.length - 1) {
                        tmpProfessionItemModel.isHaveDivider = true;
                    }
                    mProfessionItemModel.add(tmpProfessionItemModel);
                }
                mRecycleItemModel.modelList = mProfessionItemModel;
            }

            if (i == 1) {
                List<ProfessionItemModel> mProfessionItemModel = new ArrayList<>();
                ProfessionItemModel tmpProfessionItemModel;
                for (int j = 0; j < innerArrayThree.length; j++) {
                    tmpProfessionItemModel = new ProfessionItemModel();
                    tmpProfessionItemModel.name = innerArrayThree[j];
                    tmpProfessionItemModel.value = innerArrayThreeValue.get(j).toString();
                    tmpProfessionItemModel.valueUnit = innerArrayThreeUnit[j];
                    if (j == innerArrayThree.length - 1) {
                        tmpProfessionItemModel.isHaveDivider = true;
                    }
                    mProfessionItemModel.add(tmpProfessionItemModel);
                }
                mRecycleItemModel.modelList = mProfessionItemModel;
            }

            mdate.add(mRecycleItemModel);

        }
        return mdate;
    }


    @OnClick({R.id.if_left_btn, R.id.if_left_finish})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.if_left_btn) {
            finish();
        } else if (id == R.id.if_left_finish) {
            Intent intent = new Intent();
            intent.putExtra("id", "id0");
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
