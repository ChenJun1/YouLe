package com.laiding.yl.youle.Information.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.Information.entity.CommentBean;
import com.laiding.yl.youle.Information.entity.CommentListBean;
import com.laiding.yl.youle.Information.entity.InformationDetailBean;

import java.util.List;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks
 */

public interface IInformationDetail extends IBaseView {
    int getNid();

    void detailResult(InformationDetailBean bean);

    void commentResult();

    int getPage();

    void commentListResult(CommentListBean bean);

    void postdCommentResult(List<CommentListBean.MessageInfoBean> list);

    String getPostCommetText();
}
