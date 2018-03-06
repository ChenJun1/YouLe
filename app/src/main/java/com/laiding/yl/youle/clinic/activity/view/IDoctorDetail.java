package com.laiding.yl.youle.clinic.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.clinic.entity.DoctorBean;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 医生详情
 */

public interface IDoctorDetail extends IBaseView {
    void showResult(DoctorBean bean);

    String getDId();
}
