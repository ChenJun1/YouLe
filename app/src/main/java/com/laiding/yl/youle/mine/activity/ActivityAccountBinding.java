package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.login.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JunChen on 2018/2/1.
 * Remarks 账号绑定
 */

public class ActivityAccountBinding extends MyBaseActivity {

    @BindView(R.id.pone_tv)
    TextView mPoneTv;

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
        User userInfo = UserInfoManager.getUserInfo();
        mPoneTv.setText(userInfo==null?"":userInfo.getU_phone());
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
