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
    public PresenterClinic(IFragmentClinic view, FragmentClinic fragmet) {
        super(view, fragmet);
    }

    public void requestClinic(){

        final Map<String,Object> request= HttpRequest.getRequest();


        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<List<ClinicBean>>>() {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse<List<ClinicBean>> response) {
                    if(response.isSuccess()){
                        getFrgment().showResult(response.getResult());
                    }
            }
        };

        new HttpRxObservable<List<ClinicBean>>().getObservable(ApiUtlis.getClinicApi().getHospitalDetial(request),getFrgment(),FragmentEvent.STOP).subscribe(httpRxObserver);
    }



}
