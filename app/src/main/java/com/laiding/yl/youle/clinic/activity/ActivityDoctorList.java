package com.laiding.yl.youle.clinic.activity;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks 专家团队
 */

public class ActivityDoctorList extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityDoctorList.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_doctor_list;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initBundleData() {

    }
}
