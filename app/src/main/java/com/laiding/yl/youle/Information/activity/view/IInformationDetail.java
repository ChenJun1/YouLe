package com.laiding.yl.youle.information.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.information.entity.CommentListBean;
import com.laiding.yl.youle.information.entity.InformationDetailBean;


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
