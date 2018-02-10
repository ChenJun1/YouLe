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
import com.laiding.yl.youle.login.activity.ActivityUpdatePassWord;
import com.laiding.yl.youle.login.activity.ActivityPhoneLogin;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

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
    @BindView(R.id.btn_out_login)
    Button mBtnOutLogin;

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
                ActivityUpdatePassWord.start(mContext);
                break;
            case R.id.tv_about_us:
                ActivityAboutUs.start(mContext);
                break;
            case R.id.btn_out_login:
                final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mContext);//提示弹窗
                rxDialogSureCancel.setContent("是否退出登陆？");
                rxDialogSureCancel.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
                rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                });
                rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSureCancel.cancel();
                    }
                });
                rxDialogSureCancel.show();

                break;
        }
    }

}
