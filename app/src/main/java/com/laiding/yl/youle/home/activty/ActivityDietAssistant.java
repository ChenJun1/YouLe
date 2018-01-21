package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 饮食助手
 */

public class ActivityDietAssistant extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityDietAssistant.class);
        context.startActivity(starter);

    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_diet_assistant;
    }

    @Override
    protected void init() {
            setTitle("饮食助手");
            isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
