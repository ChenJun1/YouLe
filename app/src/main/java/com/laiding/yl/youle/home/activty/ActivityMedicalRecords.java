package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 诊疗记录
 */

public class ActivityMedicalRecords extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityMedicalRecords.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_medical_records;
    }

    @Override
    protected void init() {
        setTitle("诊疗记录");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
