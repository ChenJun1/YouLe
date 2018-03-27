package com.laiding.yl.youle.mine.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.information.entity.CommentBean;
import com.laiding.yl.youle.mine.entity.CommonToolBean;

import java.util.List;

/**
 * Created by JunChen on 2018/3/21.
 * Remarks
 */

public interface ICommnoTool extends IBaseView {
    void onSuccess(List<CommonToolBean> list);
}
