package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks 设置
 */

public class ActivitySet extends MyBaseActivity {
    @BindView(R.id.tv_personal_information)
    TextView mTvPersonalInformation;
    @BindView(R.id.tv_account_binding)
    TextView mTvAccountBinding;
    @BindView(R.id.tv_change_password)
    TextView mTvChangePassword;
    @BindView(R.id.tv_about_us)
    TextView mTvAboutUs;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivitySet.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_set;
    }

    @Override
    protected void init() {
        setTitle("设置");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.tv_personal_information, R.id.tv_account_binding, R.id.tv_change_password, R.id.tv_about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_personal_information:
                ActivityPersonalInformation.start(mContext);
                break;
            case R.id.tv_account_binding:
                break;
            case R.id.tv_change_password:
                break;
            case R.id.tv_about_us:
                break;
        }
    }
}
