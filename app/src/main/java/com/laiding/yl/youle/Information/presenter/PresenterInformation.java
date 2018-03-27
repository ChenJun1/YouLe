package com.laiding.yl.youle.information.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;

import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBaseFrgPresenter;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.information.entity.AdsPictures;
import com.laiding.yl.youle.information.fragment.FragmentInformation;
import com.laiding.yl.youle.information.fragment.view.IInformationFragment;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks
 */

public class PresenterInformation extends MyBaseFrgPresenter<IInformationFragment, FragmentInformation> {
    private static final String TAG = "PresenterInformationFra";

    public PresenterInformation(IInformationFragment view, FragmentInformation fragmet) {
        super(view, fragmet);
    }

    /**
     * 获取资讯列表
     *
     * @param
     * @param
     */
    public void requestHttp() {
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("p", getView().getPage());
        request.put("limit", FragmentInformation.PAGE_SIZE);

        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse<List<CommunityBean>>>(TAG + "requestHttp", getView()) {
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

                if (response.isSuccess()) {
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
     * 获取资讯广告图
     *
     * @param
     * @param
     */
    public void requestHttpAdsPictures() {
        final Map<String, Object> request = HttpRequest.getRequest();

        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse<List<AdsPictures>>>(TAG, getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {
                if (getView() != null) {
                    getView().showResultAdsPictures(null);
                }
            }

            @Override
            protected void onSuccess(HttpResponse<List<AdsPictures>> response) {

                if (getView() != null) {
                    getView().showResultAdsPictures(response.getResult());
                }
            }
        };
        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
        new HttpRxObservable<List<AdsPictures>>().getObservable(ApiUtlis.getCommunityApi().getCommunity(request), getFrgment(), FragmentEvent.STOP).subscribe(httpRxObserver);
    }
}
