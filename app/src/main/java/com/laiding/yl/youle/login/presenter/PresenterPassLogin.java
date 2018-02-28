package com.laiding.yl.youle.login.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.dao.UserDaoUtil;
import com.laiding.yl.youle.login.activity.ActivityPassLogin;
import com.laiding.yl.youle.login.activity.view.IPassLogin;
import com.laiding.yl.youle.login.entity.User;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks
 */

public class PresenterPassLogin extends MyBasePresenter<IPassLogin,ActivityPassLogin> {
    public PresenterPassLogin(IPassLogin view, ActivityPassLogin activity) {
        super(view, activity);
    }

    /**
     * 密码登录
     */
    public void login(){
        Map<String, Object> request = HttpRequest.getRequest();
//        request.put("u_phone", getActivity().getPhone());
//        request.put("u_pwd", getActivity().getPassWord());
        request.put("u_phone", "17621876063");
        request.put("u_pwd", "123");

        HttpRxObserver<HttpResponse<User>> observer=new HttpRxObserver<HttpResponse<User>>() {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse<User> response) {
                if(response.isSuccess()){
                    //持久化用户信息
                    User user = response.getResult();
                    UserDaoUtil daoUtil=new UserDaoUtil(getActivity());
                    daoUtil.deleteAll();
                    daoUtil.insertUser(user);

                    getActivity().showResult();
                }
            }
        };

        new HttpRxObservable<User>().getObservable(ApiUtlis.getUserApi().passWordLogin(request),getActivity(), ActivityEvent.STOP).subscribe(observer);
    }
}
