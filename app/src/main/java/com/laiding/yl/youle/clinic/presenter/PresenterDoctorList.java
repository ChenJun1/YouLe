package com.laiding.yl.youle.clinic.presenter;

import com.laiding.yl.mvprxretrofitlibrary.base.BasePresenter;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.clinic.activity.ActivityClinicDetail;
import com.laiding.yl.youle.clinic.activity.ActivityDoctorList;
import com.laiding.yl.youle.clinic.activity.view.IDoctorList;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 医生列表
 */

public class PresenterDoctorList extends MyBasePresenter<IDoctorList,ActivityDoctorList> {
    public PresenterDoctorList(IDoctorList view, ActivityDoctorList activity) {
        super(view, activity);
    }
}
