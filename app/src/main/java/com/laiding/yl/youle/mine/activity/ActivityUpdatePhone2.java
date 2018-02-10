package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.vondear.rxtools.RxRegTool;
import com.vondear.rxtools.RxTool;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/2/2.
 * Remarks 修改手机号
 */

public class ActivityUpdatePhone2 extends MyBaseActivity {
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_verification)
    EditText mEtVerification;
    @BindView(R.id.countDown)
    TextView mCountDown;
    @BindView(R.id.login_bt)
    Button mLoginBt;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityUpdatePhone2.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_update_phone2;
    }

    @Override
    protected void init() {
        isBack(true);
        setTitle("新手机号验证");
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick({R.id.countDown, R.id.login_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.countDown:
                checkPhone();
                break;
            case R.id.login_bt:
                ActivityUpdatePhone.mUpdatePhoneActivity.finish();
                this.finish();
                break;
        }
    }

    private void checkPhone() {
        if (RxRegTool.isMobile(String.valueOf(mEtMobile.getText()))) {
            RxTool.countDown(mCountDown, 60000, 1000, "重新获取验证码");
        } else {
            RxToast.error("请输入正确手机号");
        }

    }
}