package com.laiding.yl.youle.home.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBaseFrgPresenter;
import com.laiding.yl.youle.home.fragment.FragmentPrepareForPregnancy;
import com.laiding.yl.youle.home.fragment.view.IPregnancyFragment;
import com.laiding.yl.youle.login.entity.UserBean;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import okhttp3.FormBody;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks 备孕检查
 */

public class PresenterPregnancy extends MyBaseFrgPresenter<IPregnancyFragment,FragmentPrepareForPregnancy> {
    private static final String TAG = "PresenterPregnancyFragm";
    public PresenterPregnancy(IPregnancyFragment view, FragmentPrepareForPregnancy fragmet) {
        super(view, fragmet);
    }

    /**
     * 登陆
     * @param name
     * @param pass
     */
    public void login(String name,String pass){
        final Map<String, Object> request = HttpRequest.getRequest();

        request.put("username", name);
        request.put("password", pass);

        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<UserBean>>(TAG+"login",getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse<UserBean> response) {

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
        new HttpRxObservable<UserBean>().getObservable(ApiUtlis.getUserApi().login(request), getFrgment(), FragmentEvent.STOP).subscribe(httpRxObserver);

    }
}
