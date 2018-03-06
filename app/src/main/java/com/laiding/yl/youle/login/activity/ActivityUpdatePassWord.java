package com.laiding.yl.youle.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.login.activity.view.IUpdatePassWord;
import com.laiding.yl.youle.login.presenter.PresenterUpdatePassWord;
import com.vondear.rxtools.RxRegTool;
import com.vondear.rxtools.RxTool;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/4.
 * 修改密码
 */

public class ActivityUpdatePassWord extends MyBaseActivity implements IUpdatePassWord {


    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.code_tv)
    EditText mCodeTv;
    @BindView(R.id.countDown)
    TextView mCountDown;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.clean_password)
    ImageView mCleanPassword;
    @BindView(R.id.submit_bt)
    Button mSubmitBt;

    private PresenterUpdatePassWord presenter = new PresenterUpdatePassWord(this, this);

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

    @Override
    public String getPhone() {
        return mEtMobile.getText().toString();
    }

    @Override
    public String getCode() {
        return mCodeTv.getText().toString();
    }

    @Override
    public String getPass() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void isSuccess() {
        RxToast.success("修改成功");
        finish();
    }


    @OnClick({R.id.countDown, R.id.clean_password, R.id.submit_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.countDown:
                if (checkPhone())
                    presenter.getVerificationCode();
                break;
            case R.id.clean_password:
                mEtPassword.setText("");
                break;
            case R.id.submit_bt:
                if (checkPhoneAndCode())
                    presenter.requestUpdatePass();
                break;
        }
    }

    /**
     * 检查正确手机号
     */
    private boolean checkPhone() {

        if (getPhone().isEmpty()) {
            RxToast.warning("请输入手机号");
            return false;
        }

        if (RxRegTool.isMobile(String.valueOf(mEtMobile.getText()))) {
            RxTool.countDown(mCountDown, 60000, 1000, "重新获取验证码");
            return true;
        } else {
            RxToast.warning("请输入正确手机号");
            return false;
        }

    }

    /**
     * 检查正确手机号和验证码
     */
    private boolean checkPhoneAndCode() {

        if (getPhone().isEmpty()) {
            RxToast.warning("请输入手机号");
            return false;
        }

        if (getCode().isEmpty()) {
            RxToast.warning("请输入验证码");
            return false;
        }

        if (getPass().isEmpty()) {
            RxToast.warning("请输入密码");
            return false;
        }

        if (RxRegTool.isMobile(String.valueOf(mEtMobile.getText()))) {
            RxTool.countDown(mCountDown, 60000, 1000, "重新获取验证码");
            return true;
        } else {
            RxToast.warning("请输入正确手机号");
            return false;
        }
    }
}
