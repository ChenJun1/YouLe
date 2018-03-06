package com.laiding.yl.youle.home.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.home.activty.ActivityMedicalRecordsDetail;
import com.laiding.yl.youle.home.activty.view.IMedicalRecordsDetailActy;
import com.laiding.yl.youle.home.entity.MedicalRecordsBean;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 诊疗详情
 */

public class PresenterMedicalRecordsDetail extends MyBasePresenter<IMedicalRecordsDetailActy,ActivityMedicalRecordsDetail> {
    private static final String TAG = "PresenterMedicalRecords";
    public PresenterMedicalRecordsDetail(IMedicalRecordsDetailActy view, ActivityMedicalRecordsDetail activity) {
        super(view, activity);
    }

    /**
     *诊疗记录详情
     */
    public void requestHttp(){
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("r_id", getView().getRID());
        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<MedicalRecordsBean>>(TAG,getView()) {
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
            protected void onSuccess(HttpResponse<MedicalRecordsBean> response) {
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
        new HttpRxObservable<MedicalRecordsBean>().getObservable(ApiUtlis.getHomeApi().getRecordDetai(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }
}
