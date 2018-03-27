package com.laiding.yl.youle.information.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.information.activity.ActivityInformationDetail;
import com.laiding.yl.youle.information.activity.view.IInformationDetail;
import com.laiding.yl.youle.information.entity.CommentListBean;
import com.laiding.yl.youle.information.entity.InformationDetailBean;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 资讯详情
 */

public class PresenterInformationDetail extends MyBasePresenter<IInformationDetail, ActivityInformationDetail> {
    private static final String TAG = "PresenterInformationDet";

    public PresenterInformationDetail(IInformationDetail view, ActivityInformationDetail activity) {
        super(view, activity);
    }

    /**
     * 资讯详情
     *
     * @param
     * @param
     */
    public void requestInforMationDetail() {
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("n_id", getView().getNid());

        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse<InformationDetailBean>>(TAG + "requestInforMationDetail", getView()) {
            @Override
            protected void onStart(Disposable d) {
            }

            @Override
            protected void onError(ApiException e) {
                if (getView() != null) {
                    getView().detailResult(null);
                }
            }

            @Override
            protected void onSuccess(HttpResponse<InformationDetailBean> response) {

                if (response.isSuccess()) {
                    getView().detailResult(response.getResult());
                }
            }
        };
        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
        new HttpRxObservable<InformationDetailBean>().getObservable(ApiUtlis.getCommunityApi().getNewsDetail(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }

    /**
     * 社区评论列表
     *
     * @param
     * @param
     */
    public void requestCommentList() {
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("n_id", getView().getNid());
        request.put("p", getView().getPage());
        request.put("limit", ActivityInformationDetail.PAGE_SIZE);

        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse<CommentListBean>>(TAG + "requestCommentList", getView()) {
            @Override
            protected void onStart(Disposable d) {
            }

            @Override
            protected void onError(ApiException e) {
                if (getView() != null) {
                    getView().commentListResult(null);
                }
            }

            @Override
            protected void onSuccess(HttpResponse<CommentListBean> response) {

                if (response.isSuccess()) {
                    getView().commentListResult(response.getResult());
                }
            }
        };
        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
        new HttpRxObservable<CommentListBean>().getObservable(ApiUtlis.getCommunityApi().getCommentList(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }

    /**
     * 发表社区评论
     *
     * @param
     * @param
     */
    public void postedComment() {
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_id", UserInfoManager.getUserInfo().getU_id());
        request.put("m_message", getView().getPostCommetText());
        request.put("n_id", getView().getNid());
        request.put("token", UserInfoManager.getUserInfo().getToken());

        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse<List<CommentListBean.MessageInfoBean>>>(TAG + "postedComment", getView()) {
            @Override
            protected void onStart(Disposable d) {
            }

            @Override
            protected void onError(ApiException e) {
                getView().postdCommentResult(null);
            }

            @Override
            protected void onSuccess(HttpResponse<List<CommentListBean.MessageInfoBean>> response) {

                if (response.isSuccess()) {
                    getView().postdCommentResult(response.getResult());
                }
            }
        };
        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
        new HttpRxObservable<List<CommentListBean.MessageInfoBean>>().getObservable(ApiUtlis.getCommunityApi().postedComment(request), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);

    }
}
