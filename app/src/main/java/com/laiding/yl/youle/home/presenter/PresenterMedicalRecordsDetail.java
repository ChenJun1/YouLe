package com.laiding.yl.youle.home.presenter;

import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.home.activty.ActivityMedicalRecordsDetail;
import com.laiding.yl.youle.home.activty.view.IMedicalRecordsDetailActy;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 诊疗详情
 */

public class PresenterMedicalRecordsDetail extends MyBasePresenter<IMedicalRecordsDetailActy,ActivityMedicalRecordsDetail> {
    public PresenterMedicalRecordsDetail(IMedicalRecordsDetailActy view, ActivityMedicalRecordsDetail activity) {
        super(view, activity);
    }
}
