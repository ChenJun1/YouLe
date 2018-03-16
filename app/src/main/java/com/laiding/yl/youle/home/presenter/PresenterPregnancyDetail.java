package com.laiding.yl.youle.home.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.home.activty.ActivityPregnancyDetail;
import com.laiding.yl.youle.home.activty.view.IPregnancyDetail;
import com.laiding.yl.youle.home.entity.PregnancyDetailBean;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/3/12.
 * Remarks 备孕详情
 */

public class PresenterPregnancyDetail extends MyBasePresenter<IPregnancyDetail, ActivityPregnancyDetail> {
    private static final String TAG = "PresenterPregnancyDetai";

    public PresenterPregnancyDetail(IPregnancyDetail view, ActivityPregnancyDetail activity) {
        super(view, activity);
    }

    /**
     * 备孕详情
     */
    public void requestHttp() {
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("p_id", getView().getPid());
        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse<PregnancyDetailBean>>(TAG, getView()) {
            @Override
            protected void onStart(Disposable d) {
            }

            @Override
            protected void onError(ApiException e) {
                if (getView() != null) {
                    getView().SuccessResult(null);
                }
            }

            @Override
            protected void onSuccess(HttpResponse<PregnancyDetailBean> response) {
                if (getView() != null) {
                    getView().SuccessResult(response.getResult());
                }
            }
        };
        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
        new HttpRxObservable<PregnancyDetailBean>().getObservable(ApiUtlis.getHomeApi().getPregnantDetail(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }
}
