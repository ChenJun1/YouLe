package com.laiding.yl.youle.base;

/**
 * Created by JunChen on 2018/1/18.
 * Remarks
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.laiding.yl.mvprxretrofitlibrary.base.BaseFragment;
import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.login.activity.ActivityPhoneLogin;
import com.laiding.yl.youle.login.entity.User;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

public abstract class MyBaseFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    /**
     *  token 过期重新登录
     * @param erreMsg
     */
    @Override
    public void isTokenExpired(String erreMsg) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mContext);//提示弹窗
        rxDialogSureCancel.setContent(erreMsg);
        rxDialogSureCancel.getTitleView().setBackgroundResource(R.mipmap.home_log);
        rxDialogSureCancel.getSureView().setOnClickListener(v ->{
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
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(v -> rxDialogSureCancel.cancel());
        rxDialogSureCancel.show();
    }
    @Override
    public boolean isLogin() {
        User user = UserInfoManager.getUserInfo();
        return !user.getToken().isEmpty();
    }
}
