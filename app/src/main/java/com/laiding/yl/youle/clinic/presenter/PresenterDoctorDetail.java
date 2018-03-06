package com.laiding.yl.youle.clinic.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.clinic.activity.ActivityDoctorDetail;
import com.laiding.yl.youle.clinic.activity.view.IDoctorDetail;
import com.laiding.yl.youle.clinic.entity.ClinicDetailBean;
import com.laiding.yl.youle.clinic.entity.DoctorBean;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks
 */

public class PresenterDoctorDetail extends MyBasePresenter<IDoctorDetail,ActivityDoctorDetail> {
    private static final String TAG = "PresenterDoctorDetail";
    public PresenterDoctorDetail(IDoctorDetail view, ActivityDoctorDetail activity) {
        super(view, activity);
    }
    public void requestClinicDetail(){

        final Map<String,Object> request= HttpRequest.getRequest();
        request.put("d_id",getView().getDId());

        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<DoctorBean>>(TAG,getView()) {
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
            protected void onSuccess(HttpResponse<DoctorBean> response) {
                if(response.isSuccess()){
                    getView().showResult(response.getResult());
                }
            }
        };

        new HttpRxObservable<DoctorBean>().getObservable(ApiUtlis.getClinicApi().getDoctorsDetail(request),getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }
}
