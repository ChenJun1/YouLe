package com.laiding.yl.youle.home.activty.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.home.entity.PregnancyDetailBean;

/**
 * Created by JunChen on 2018/3/12.
 * Remarks
 */

public interface IPregnancyDetail extends IBaseView {
    int getPid();

    void SuccessResult(PregnancyDetailBean bean);
}
