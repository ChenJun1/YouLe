package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 试管婴儿成功率
 */

public class ActivityIVFSuccessRate extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityIVFSuccessRate.class);
        context.startActivity(starter);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_ivf_success_rate;
    }

    @Override
    protected void init() {
        setTitle("试管婴儿成功率");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
