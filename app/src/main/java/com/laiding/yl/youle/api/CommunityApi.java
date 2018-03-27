package com.laiding.yl.youle.api;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.information.entity.AdsPictures;
import com.laiding.yl.youle.information.entity.CommentListBean;
import com.laiding.yl.youle.information.entity.InformationDetailBean;
import com.laiding.yl.youle.home.entity.CommunityBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by JunChen on 2018/2/26.
 * Remarks
 */

public interface CommunityApi {
//    /**
//     * 社区图文章
//     * @param request
//     * @return
//     */
//    @FormUrlEncoded
//    @GET("?r=news/news_community")
//    Observable<HttpResponse> getNewsCommunity(@QueryMap Map<String, Object> request);


    /**
     * 社区列表
     * @param request
     * @return
     */
    @GET("?r=news/news")
    Observable<HttpResponse<List<CommunityBean>>> getNewsList(@QueryMap Map<String, Object> request);


    /**
     * 社区详情
     * @param request
     * @return
     */
    @GET("?r=news/news_info")
    Observable<HttpResponse<InformationDetailBean>> getNewsDetail(@QueryMap Map<String, Object> request);

    /**
     * 社区广告位
     * @param request
     * @return
     */
    @GET("?r=news/ad_community")
    Observable<HttpResponse<List<AdsPictures>>> getCommunity(@QueryMap Map<String, Object> request);

    /**
     * 社区发表评论
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=news/message")
    Observable<HttpResponse<List<CommentListBean.MessageInfoBean>>> postedComment(@FieldMap Map<String, Object> request);

    /**
     * 社区评论列表
     * @param request
     * @return
     */
    @GET("?r=news/message_info")
    Observable<HttpResponse<CommentListBean>> getCommentList(@QueryMap Map<String, Object> request);
}
