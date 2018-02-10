package com.laiding.yl.youle.Information.presenter;

import com.laiding.yl.youle.Information.activity.ActivityInformationDetail;
import com.laiding.yl.youle.Information.activity.view.IInformationDetail;
import com.laiding.yl.youle.base.MyBasePresenter;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 资讯详情
 */

public class PresenterInformationDetail extends MyBasePresenter<IInformationDetail, ActivityInformationDetail> {
    public PresenterInformationDetail(IInformationDetail view, ActivityInformationDetail activity) {
        super(view, activity);
    }
}
