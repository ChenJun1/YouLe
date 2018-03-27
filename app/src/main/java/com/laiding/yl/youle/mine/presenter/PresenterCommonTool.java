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
import com.laiding.yl.youle.mine.activity.ActivityCommonTool;
import com.laiding.yl.youle.mine.activity.view.ICommnoTool;
import com.laiding.yl.youle.mine.entity.CommonToolBean;
import com.laiding.yl.youle.mine.entity.UserInfo;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/3/21.
 * Remarks
 */

public class PresenterCommonTool extends MyBasePresenter<ICommnoTool,ActivityCommonTool> {
    private static final String TAG = "PresenterCommonTool";
    public PresenterCommonTool(ICommnoTool view, ActivityCommonTool activity) {
        super(view, activity);
    }

    /**
     * 获取个人信息
     */
    public void requestCommnoTool() {
        final Map<String, Object> request = HttpRequest.getRequest();
        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<List<CommonToolBean>>>(TAG,getView()) {
            @Override
            protected void onStart(Disposable d) {}

            @Override
            protected void onError(ApiException e) {
                getView().onSuccess(null);
            }

            @Override
            protected void onSuccess(HttpResponse<List<CommonToolBean>> response) {
                if (response.isSuccess())
                    getView().onSuccess(response.getResult());
            }
        };

        new HttpRxObservable<List<CommonToolBean>>().getObservable(ApiUtlis.getUserApi().getAssistantInfo(request),getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }
}
