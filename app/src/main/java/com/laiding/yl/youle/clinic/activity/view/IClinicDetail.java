package com.laiding.yl.youle.clinic.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.clinic.entity.ClinicDetailBean;

/**
 * Created by JunChen on 2018/1/29.
 * Remarks 诊所列表
 */

public interface IClinicDetail extends IBaseView {
    void showResult(ClinicDetailBean detailBean);

    String getClinicId();
}
