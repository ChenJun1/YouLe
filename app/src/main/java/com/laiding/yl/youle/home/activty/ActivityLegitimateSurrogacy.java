package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 合法代孕
 */

public class ActivityLegitimateSurrogacy extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityLegitimateSurrogacy.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_legitimate_surrogacy;
    }

    @Override
    protected void init() {
        setTitle("合法代孕");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
