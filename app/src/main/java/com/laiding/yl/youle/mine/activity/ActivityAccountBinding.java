package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/2/1.
 * Remarks 账号绑定
 */

public class ActivityAccountBinding extends MyBaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityAccountBinding.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_account_binding;
    }

    @Override
    protected void init() {
        setTitle("账号绑定");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
