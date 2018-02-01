package com.laiding.yl.youle.clinic.activity;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks医生详情
 */

public class ActivityDoctorDetail extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityDoctorDetail.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_doctor_detail;
    }

    @Override
    protected void init() {
        setTitle("医生详情");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
