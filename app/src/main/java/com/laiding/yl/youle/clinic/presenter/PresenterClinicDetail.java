package com.laiding.yl.youle.clinic.presenter;

import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.clinic.activity.ActivityClinicDetail;
import com.laiding.yl.youle.clinic.activity.view.IClinicDetail;

/**
 * Created by JunChen on 2018/1/29.
 * Remarks
 */

public class PresenterClinicDetail extends MyBasePresenter<IClinicDetail,ActivityClinicDetail> {
    public PresenterClinicDetail(IClinicDetail view, ActivityClinicDetail activity) {
        super(view, activity);
    }
}
