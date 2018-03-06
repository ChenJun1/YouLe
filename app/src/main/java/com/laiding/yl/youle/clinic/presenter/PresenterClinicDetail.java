package com.laiding.yl.youle.clinic.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.clinic.activity.ActivityClinicDetail;
import com.laiding.yl.youle.clinic.activity.view.IClinicDetail;
import com.laiding.yl.youle.clinic.entity.ClinicBean;
import com.laiding.yl.youle.clinic.entity.ClinicDetailBean;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/1/29.
 * Remarks 诊所详情
 */

public class PresenterClinicDetail extends MyBasePresenter<IClinicDetail,ActivityClinicDetail> {
    private static final String TAG = "PresenterClinicDetail";
    public PresenterClinicDetail(IClinicDetail view, ActivityClinicDetail activity) {
        super(view, activity);
    }

    public void requestClinicDetail(){

        final Map<String,Object> request= HttpRequest.getRequest();
        request.put("h_id",getView().getClinicId());

        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<ClinicDetailBean>>(TAG,getView()) {
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
            protected void onSuccess(HttpResponse<ClinicDetailBean> response) {
                if(response.isSuccess()){
                    getView().showResult(response.getResult());
                }
            }
        };

        new HttpRxObservable<ClinicDetailBean>().getObservable(ApiUtlis.getClinicApi().getHospitalDetial(request),getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }

}
