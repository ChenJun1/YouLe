package com.laiding.yl.youle.login.presenter;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.login.activity.ActivityPhoneLogin;
import com.laiding.yl.youle.login.activity.view.ILoginView;
import com.laiding.yl.youle.login.entity.UserBean;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/1/3.
 */

public class PresenterLogin extends MyBasePresenter<ILoginView,ActivityPhoneLogin> {

    private static final String TAG = "PresenterLogin";

    public PresenterLogin(ILoginView view, ActivityPhoneLogin activity) {
        super(view, activity);
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

        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<UserBean>>(TAG+"login") {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse<UserBean> response) {
                LogUtils.d(request.toString()+"========");
                response.getResult();
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
        new HttpRxObservable<UserBean>().getObservable(ApiUtlis.getLoginApi().login(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }

   public void login(){
        getView().showLoading();
       EMClient.getInstance().login("8811", "123456", new EMCallBack() {
           @Override
           public void onSuccess() {
               EMClient.getInstance().groupManager().loadAllGroups();
               EMClient.getInstance().chatManager().loadAllConversations();
               getView().closeLoading();
               LogUtils.e("登录成功"+EMClient.getInstance().isConnected());
               if(EMClient.getInstance().isConnected()){
                  getView().toChat();
              }
           }
           @Override
           public void onError(int i, String s) {
               getView().closeLoading();
               LogUtils.e("登录失败"+s);
           }

           @Override
           public void onProgress(int i, String s) {

           }
       });
   }
    
}
