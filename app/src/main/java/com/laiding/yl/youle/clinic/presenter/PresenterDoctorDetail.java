package com.laiding.yl.youle.clinic.presenter;

import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.clinic.activity.ActivityDoctorDetail;
import com.laiding.yl.youle.clinic.activity.view.IDoctorDetail;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks
 */

public class PresenterDoctorDetail extends MyBasePresenter<IDoctorDetail,ActivityDoctorDetail> {
    public PresenterDoctorDetail(IDoctorDetail view, ActivityDoctorDetail activity) {
        super(view, activity);
    }
}
