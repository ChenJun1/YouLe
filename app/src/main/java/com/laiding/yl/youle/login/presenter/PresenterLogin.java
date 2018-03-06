package com.laiding.yl.youle.login.presenter;

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
import com.laiding.yl.youle.dao.UserDaoUtil;
import com.laiding.yl.youle.login.activity.ActivityPhoneLogin;
import com.laiding.yl.youle.login.activity.view.ILoginView;
import com.laiding.yl.youle.login.entity.User;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.vondear.rxtools.view.RxToast;

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
     */
    public void login(){
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_phone", getView().getPhone());
        request.put("v_code", getView().getVerificationCode());

        HttpRxObserver<HttpResponse<User>> httpRxObserver=new HttpRxObserver<HttpResponse<User>>(TAG+"login",getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {
                getActivity().showError("登录失败");
            }

            @Override
            protected void onSuccess(HttpResponse<User> response) {
                LogUtils.d(request.toString()+"========");
                if (getView() != null) {
                    //持久化用户信息
                    User user = response.getResult();
                    UserDaoUtil daoUtil=new UserDaoUtil(getActivity());
                    daoUtil.deleteAll();
//                    daoUtil.insertUser(user);
                    getView().showResult();
                    loginIM();//登录环信
                }
            }
        };
        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
        new HttpRxObservable<User>().getObservable(ApiUtlis.getUserApi().codeLogin(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }


    /**
     * 获取验证码
     *
     */
    public void getVerificationCode(){
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_phone", getView().getPhone());
        request.put("v_type", 0); //0（注册和登陆）

        HttpRxObserver<HttpResponse> httpRxObserver=new HttpRxObserver<HttpResponse>(TAG+"getVerificationCode",getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {
                RxToast.error("验证码发送失败");
            }

            @Override
            protected void onSuccess(HttpResponse response) {
                if(response.isSuccess()){
                    RxToast.success("验证码发送成功");

                }
                LogUtils.d(response.toString()+"========");
            }
        };

        new HttpRxObservable().getObservable(ApiUtlis.getUserApi().getVerificationCode(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }

    /**
     * 环信登录
     */
   public void loginIM(){
//        getView().showLoading();
       EMClient.getInstance().login("8811", "123456", new EMCallBack() {
           @Override
           public void onSuccess() {
               EMClient.getInstance().groupManager().loadAllGroups();
               EMClient.getInstance().chatManager().loadAllConversations();
//               getView().closeLoading();

               if(EMClient.getInstance().isConnected()){
                   LogUtils.i("环信登录成功"+EMClient.getInstance().isConnected());
              }
           }
           @Override
           public void onError(int i, String s) {
//               getView().closeLoading();
               LogUtils.e("环信登录失败"+s);
           }

           @Override
           public void onProgress(int i, String s) {

           }
       });
   }
    
}
