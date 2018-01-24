package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks  自定义添加诊疗记录
 */

public class ActivityAddMedicalRecords extends MyBaseActivity {


    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityAddMedicalRecords.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_medical_records;
    }

    @Override
    protected void init() {
        initView();

    }

    private void initView() {
        setTitle("添加");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
