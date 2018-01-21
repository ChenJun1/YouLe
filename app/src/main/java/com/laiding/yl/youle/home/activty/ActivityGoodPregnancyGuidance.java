package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 好孕指导
 */

public class ActivityGoodPregnancyGuidance extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityGoodPregnancyGuidance.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_good_pregnancy_guidance;
    }

    @Override
    protected void init() {
        setTitle("好孕指导");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
