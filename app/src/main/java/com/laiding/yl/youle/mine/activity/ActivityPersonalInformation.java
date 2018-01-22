package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks 个人资料
 */

public class ActivityPersonalInformation extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityPersonalInformation.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void init() {
        setTitle("个人资料");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
