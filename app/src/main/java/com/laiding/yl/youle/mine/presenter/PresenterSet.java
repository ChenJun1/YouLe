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
import com.laiding.yl.youle.mine.activity.ActivitySet;
import com.laiding.yl.youle.mine.activity.view.ISet;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/3/1.
 * Remarks
 */

public class PresenterSet extends MyBasePresenter<ISet,ActivitySet> {
    private static final String TAG = "PresenterSet";
    public PresenterSet(ISet view, ActivitySet activity) {
        super(view, activity);
    }

    public void requestSetPass(){

        final User user = UserInfoManager.getUserInfo();
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_id", user.getU_id());

        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse>(TAG,getView()) {
            @Override
            protected void onStart(Disposable d) {}

            @Override
            protected void onError(ApiException e) {}

            @Override
            protected void onSuccess(HttpResponse response) {
                getView().isSuccess();
            }
        };

        new HttpRxObservable().getObservable(ApiUtlis.getUserApi().loginOut(request),getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }
}
