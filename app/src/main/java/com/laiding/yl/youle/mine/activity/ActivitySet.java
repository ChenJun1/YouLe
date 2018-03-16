package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.login.activity.ActivityUpdatePassWord;
import com.laiding.yl.youle.login.activity.ActivityPhoneLogin;
import com.laiding.yl.youle.mine.activity.view.ISet;
import com.laiding.yl.youle.mine.activity.view.ISetPass;
import com.laiding.yl.youle.mine.presenter.PresenterSet;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks 设置
 */

public class ActivitySet extends MyBaseActivity implements ISet {

    @BindView(R.id.tv_personal_information)
    TextView mTvPersonalInformation;
    @BindView(R.id.tv_account_binding)
    TextView mTvAccountBinding;
    @BindView(R.id.tv_change_password)
    TextView mTvChangePassword;
    @BindView(R.id.tv_about_us)
    TextView mTvAboutUs;
    @BindView(R.id.btn_out_login)
    Button mBtnOutLogin;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivitySet.class);
        context.startActivity(starter);
    }

    private boolean flag = true;

    private PresenterSet mPresenterSet = new PresenterSet(this, this);

    @Override
    protected int getContentViewId() {
        return R.layout.activity_set;
    }

    @Override
    protected void init() {
        setTitle("设置");
        isBack(true);
        initTextView();
    }

    /**
     * 判断是否设置过密码
     */
    private void initTextView(){
        if (UserInfoManager.getUserInfo().getU_pwd().isEmpty()) {
            mTvChangePassword.setText("设置密码");
            flag = true;
        } else {
            mTvChangePassword.setText("修改密码");
            flag = false;
        }
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.btn_out_login, R.id.tv_personal_information, R.id.tv_account_binding, R.id.tv_change_password, R.id.tv_about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_personal_information:
                ActivityPersonalInformation.start(mContext);
                break;
            case R.id.tv_account_binding:
                ActivityAccountBinding.start(mContext);
                break;
            case R.id.tv_change_password:
                if (flag)
                    ActivitySetPass.start(mContext);
                else
                    ActivityUpdatePassWord.start(mContext);
                break;
            case R.id.tv_about_us:
                ActivityAboutUs.start(mContext);
                break;
            case R.id.btn_out_login:
                final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mContext);//提示弹窗
                rxDialogSureCancel.setContent("是否退出登陆？");
                rxDialogSureCancel.getTitleView().setBackgroundResource(R.mipmap.home_log);
                rxDialogSureCancel.getSureView().setOnClickListener(v -> mPresenterSet.requestSetPass());
                rxDialogSureCancel.getCancelView().setOnClickListener(v -> rxDialogSureCancel.cancel());
                rxDialogSureCancel.show();
                break;
        }
    }

    @Override
    public void isSuccess() {
        RichText.recycle();//富文本加载框架
        RxToast.success("退出成功");
        ActivityStackManager.getManager().finishAllActivity();
        ActivityPhoneLogin.start(mContext);
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                LogUtils.e("推出成功");
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub
                LogUtils.e("推出失败");
            }
        });
    }
}
