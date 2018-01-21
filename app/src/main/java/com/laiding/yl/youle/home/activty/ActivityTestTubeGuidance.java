package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 试管指导
 */

public class ActivityTestTubeGuidance extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityTestTubeGuidance.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_test_tube_guidance;
    }

    @Override
    protected void init() {
        setTitle("试管指导");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
