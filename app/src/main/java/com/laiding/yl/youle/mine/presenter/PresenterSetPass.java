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
import com.laiding.yl.youle.mine.activity.ActivitySetPass;
import com.laiding.yl.youle.mine.activity.view.ISetPass;
import com.laiding.yl.youle.mine.entity.UserInfo;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/3/1.
 * Remarks
 */

public class PresenterSetPass extends MyBasePresenter<ISetPass,ActivitySetPass> {
    private static final String TAG = "PresenterSetPass";
    public PresenterSetPass(ISetPass view, ActivitySetPass activity) {
        super(view, activity);
    }

    public void requestSetPass(){

        final User user = UserInfoManager.getUserInfo();
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_id", user.getU_id());
        request.put("token", user.getToken());
        request.put("u_pwd",getView().getPass());
        request.put("pass", getView().getPass2());

        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse>(TAG,getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse response) {
                getView().isSuccess();
            }
        };

        new HttpRxObservable().getObservable(ApiUtlis.getUserApi().setPwd(request),getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }
}
