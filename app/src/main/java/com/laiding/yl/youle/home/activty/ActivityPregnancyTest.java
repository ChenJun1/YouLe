package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 备孕检查
 */

public class ActivityPregnancyTest extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityPregnancyTest.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_pregnancy_test;
    }

    @Override
    protected void init() {
            setTitle("备孕检查");
            isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
