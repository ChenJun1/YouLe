package com.laiding.yl.youle.clinic.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBaseFrgPresenter;
import com.laiding.yl.youle.clinic.entity.ClinicBean;
import com.laiding.yl.youle.clinic.fragment.FragmentClinic;
import com.laiding.yl.youle.clinic.fragment.view.IFragmentClinic;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/1/29.
 * Remarks 诊所
 */

public class PresenterClinic extends MyBaseFrgPresenter<IFragmentClinic,FragmentClinic> {
    private static final String TAG = "PresenterClinic";
    public PresenterClinic(IFragmentClinic view, FragmentClinic fragmet) {
        super(view, fragmet);
    }

    public void requestClinicList(){

        final Map<String,Object> request= HttpRequest.getRequest();
        request.put("p", getView().getPage());
        request.put("limit", FragmentClinic.PAGE_SIZE);
        request.put("h_name", getView().getName());
        request.put("h_info", getView().getInfo());
        request.put("h_grade", getView().getGrade());
        request.put("h_service", getView().getService());
        request.put("hid", getView().getAddress());


        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<ClinicBean>>(TAG,getView()) {
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
            protected void onSuccess(HttpResponse<ClinicBean> response) {
                    if(response.isSuccess()){
                        getView().showResult(response.getResult());
                    }
            }
        };

        new HttpRxObservable<ClinicBean>().getObservable(ApiUtlis.getClinicApi().getHospitalList(request),getFrgment(),FragmentEvent.STOP).subscribe(httpRxObserver);
    }



}
