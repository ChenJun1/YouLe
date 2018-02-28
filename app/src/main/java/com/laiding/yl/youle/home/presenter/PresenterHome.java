package com.laiding.yl.youle.home.presenter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;

import com.google.gson.Gson;
import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBaseFrgPresenter;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.home.fragment.FragmentHome;
import com.laiding.yl.youle.home.fragment.view.IHomeFragment;
import com.laiding.yl.youle.login.entity.UserBean;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/1/4.
 * Remarks 首页
 */

public class PresenterHome extends MyBaseFrgPresenter<IHomeFragment,FragmentHome> {
    private static final String TAG = "PresenterHome";
    public PresenterHome(FragmentHome view, FragmentHome fragmet) {
        super(view, fragmet);
    }

    /**
     * 登陆
     * @param
     * @param
     */
    public void requestHttp(){
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("p", 1);
        request.put("limit", 5);

        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<List<CommunityBean>>>(TAG+"login") {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {
                if (getView() != null) {
                    getView().showResult(null);
                }
            }

            @Override
            protected void onSuccess(HttpResponse<List<CommunityBean>> response) {

                if (getView() != null) {
                    getView().showResult(response.getResult());
                }
            }
        };
        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
       new HttpRxObservable<List<CommunityBean>>().getObservable(ApiUtlis.getCommunityApi().getNewsList(request), getFrgment(), FragmentEvent.STOP).subscribe(httpRxObserver);
    }

    /**
     * 跳转日历日程
     */
    public void startCalendar(){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        getFrgment().startActivity(intent);
    }
}
