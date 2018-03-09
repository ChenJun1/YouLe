package com.laiding.yl.youle.mine.fragment.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBaseFrgPresenter;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.login.entity.User;
import com.laiding.yl.youle.mine.entity.UserInfo;
import com.laiding.yl.youle.mine.fragment.FragmentMine;
import com.laiding.yl.youle.mine.fragment.view.IFragmentMine;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/3/2.
 * Remarks
 */

public class PresenterMine extends MyBaseFrgPresenter<IFragmentMine, FragmentMine> {
    private static final String TAG = "PresenterMine";

    public PresenterMine(IFragmentMine view, FragmentMine activity) {
        super(view, activity);
    }

    /**
     * 获取个人信息
     */
    public void requestUserInfo() {

        final User user = UserInfoManager.getUserInfo();
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_id", user.getU_id());
        request.put("token", user.getToken());

        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse<UserInfo>>(TAG, getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse<UserInfo> response) {
                if (response.isSuccess())
                    getView().showResult(response.getResult());
            }
        };

        new HttpRxObservable<UserInfo>().getObservable(ApiUtlis.getUserApi().getUserInfo(request), getFrgment(), FragmentEvent.STOP).subscribe(httpRxObserver);
    }


}
