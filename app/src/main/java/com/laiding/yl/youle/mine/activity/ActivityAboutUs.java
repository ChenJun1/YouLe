package com.laiding.yl.youle.mine.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.vondear.rxtools.RxAppTool;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JunChen on 2018/2/1.
 * Remarks 关于我们
 */

public class ActivityAboutUs extends MyBaseActivity {
    @BindView(R.id.version)
    TextView mVersion;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityAboutUs.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_about_us;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void init() {
        setTitle("关于我们");
        isBack(true);
        mVersion.setText("V "+RxAppTool .getAppVersionName(MyApplication.app));
    }

    @Override
    protected void initBundleData() {

    }

}
