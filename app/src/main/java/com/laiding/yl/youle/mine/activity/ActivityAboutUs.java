package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/2/1.
 * Remarks 关于我们
 */

public class ActivityAboutUs extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityAboutUs.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void init() {
        setTitle("关于我们");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
