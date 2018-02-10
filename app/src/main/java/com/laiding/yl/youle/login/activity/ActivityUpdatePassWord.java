package com.laiding.yl.youle.login.activity;

import android.content.Context;
import android.content.Intent;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

/**
 * Created by JunChen on 2018/1/4.
 * 修改密码
 */

public class ActivityUpdatePassWord extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityUpdatePassWord.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_get_pass_word;
    }

    @Override
    protected void init() {
        setTitle("修改密码");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }
}
