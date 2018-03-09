package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.mine.activity.view.ISetPass;
import com.laiding.yl.youle.mine.presenter.PresenterSetPass;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/3/1.
 * Remarks 设置密码
 */

public class ActivitySetPass extends MyBaseActivity implements ISetPass {
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.clean_password)
    ImageView mCleanPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView mIvShowPwd;
    @BindView(R.id.et_password2)
    EditText mEtPassword2;
    @BindView(R.id.clean_password2)
    ImageView mCleanPassword2;
    @BindView(R.id.iv_show_pwd2)
    ImageView mIvShowPwd2;
    @BindView(R.id.login_bt)
    Button mLoginBt;

    private PresenterSetPass presenter = new PresenterSetPass(this, this);

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivitySetPass.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_set_pass;
    }

    @Override
    protected void init() {
        isBack(true);
        setTitle("设置密码");
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.iv_show_pwd, R.id.iv_show_pwd2, R.id.login_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show_pwd:
                if (mEtPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_visuable);
                } else {
                    mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_gone);
                }
                String pwd = mEtPassword.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    mEtPassword.setSelection(pwd.length());
                break;
            case R.id.iv_show_pwd2:
                if (mEtPassword2.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtPassword2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvShowPwd2.setImageResource(R.drawable.pass_visuable);
                } else {
                    mEtPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvShowPwd2.setImageResource(R.drawable.pass_gone);
                }
                String pwd2 = mEtPassword2.getText().toString();
                if (!TextUtils.isEmpty(pwd2))
                    mEtPassword2.setSelection(pwd2.length());
                break;
            case R.id.login_bt:
                if (checkPass())
                    presenter.requestSetPass();
                break;
        }
    }

    private boolean checkPass() {
        if (getPass().toString().isEmpty() || getPass2().toString().isEmpty()) {
            RxToast.warning("请输入密码");
            return false;
        }

        if (!getPass().toString().equals(getPass2().toString())) {
            RxToast.warning("密码不一致");
            return false;
        }
        return true;
    }

    @Override
    public CharSequence getPass() {
        return mEtPassword.getText();
    }

    @Override
    public CharSequence getPass2() {
        return mEtPassword2.getText();
    }

    @Override
    public void isSuccess() {
        RxToast.success("设置成功");
        finish();
    }
}
