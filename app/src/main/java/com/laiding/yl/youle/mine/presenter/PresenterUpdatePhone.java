package com.laiding.yl.youle.mine.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.login.entity.User;
import com.laiding.yl.youle.mine.activity.ActivityUpdatePhone2;
import com.laiding.yl.youle.mine.activity.view.IUpdatePhone;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/3/1.
 * Remarks
 */

public class PresenterUpdatePhone extends MyBasePresenter<IUpdatePhone, ActivityUpdatePhone2> {
    private static final String TAG = "PresenterUpdatePhone";

    public PresenterUpdatePhone(IUpdatePhone view, ActivityUpdatePhone2 activity) {
        super(view, activity);
    }

    /**
     * 修改手机号
     */
    public void requestHttp() {
        final User user = UserInfoManager.getUserInfo();
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_id", user.getU_id());
        request.put("token", user.getToken());
        request.put("u_phone", getView().getPhone());
        request.put("v_code", getView().getCode());

        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse>(TAG, getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse response) {
                if(response.isSuccess()){
                    RxToast.success(response.getMsg());
                    getView().showResult();
                }
            }
        };

        new HttpRxObservable().getObservable(ApiUtlis.getUserApi().updatePhone(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }

    /**
     * 获取验证码
     */
    public void getVerificationCode(){
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_phone", getView().getPhone());
        request.put("v_type", 1); //0（注册和登陆）

        HttpRxObserver<HttpResponse> httpRxObserver=new HttpRxObserver<HttpResponse>(TAG+"getVerificationCode",getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {
                RxToast.error("验证码发送失败");
            }

            @Override
            protected void onSuccess(HttpResponse response) {
                if(response.isSuccess()) {
                    RxToast.success("验证码发送成功");
                }
            }
        };

        new HttpRxObservable().getObservable(ApiUtlis.getUserApi().getVerificationCode(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }
}
