package com.laiding.yl.youle.login.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.login.activity.ActivityUpdatePassWord;
import com.laiding.yl.youle.login.activity.view.IUpdatePassWord;
import com.laiding.yl.youle.login.entity.User;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/3/1.
 * Remarks
 */

public class PresenterUpdatePassWord extends MyBasePresenter<IUpdatePassWord, ActivityUpdatePassWord> {
    private static final String TAG = "PresenterUpdatePassWord";

    public PresenterUpdatePassWord(IUpdatePassWord view, ActivityUpdatePassWord activity) {
        super(view, activity);
    }

    /**
     * 修改密码
     */
    public void requestUpdatePass() {
        final Map<String, Object> request = HttpRequest.getRequest();
        final User userInfo = UserInfoManager.getUserInfo();
        request.put("u_phone", getView().getPhone());
        request.put("v_code", getView().getCode());
        request.put("u_pwd", getView().getPass());
        request.put("pass", getView().getPass());
        request.put("u_id", userInfo.getU_id());
        request.put("token", userInfo.getToken());

        HttpRxObserver<HttpResponse> httpRxObserver = new HttpRxObserver<HttpResponse>(TAG, getView()) {
            @Override
            protected void onStart(Disposable d) {}

            @Override
            protected void onError(ApiException e) {}

            @Override
            protected void onSuccess(HttpResponse response) {
                getView().isSuccess();
            }
        };

        new HttpRxObservable().getObservable(ApiUtlis.getUserApi().updatePwd(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }


    /**
     * 获取验证码
     */
    public void getVerificationCode() {
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_phone", getView().getPhone());
        request.put("v_type", 2); //0（注册和登陆）

        HttpRxObserver<HttpResponse> httpRxObserver = new HttpRxObserver<HttpResponse>(TAG + "getVerificationCode", getView()) {
            @Override
            protected void onStart(Disposable d) {}

            @Override
            protected void onError(ApiException e) {
                RxToast.error("验证码发送失败");
            }

            @Override
            protected void onSuccess(HttpResponse response) {
                if (response.isSuccess()) {
                    RxToast.success("验证码发送成功");
                }
                LogUtils.d(response.toString() + "========");
            }
        };

        new HttpRxObservable().getObservable(ApiUtlis.getUserApi().getVerificationCode(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }
}
