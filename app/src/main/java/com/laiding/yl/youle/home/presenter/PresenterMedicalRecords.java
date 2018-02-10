package com.laiding.yl.youle.home.presenter;

import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.home.activty.ActivityAddMedicalRecords;
import com.laiding.yl.youle.home.activty.ActivityMedicalRecords;
import com.laiding.yl.youle.home.activty.view.IMedicalRecordsActy;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 诊疗记录
 */

public class PresenterMedicalRecords extends MyBasePresenter<IMedicalRecordsActy,ActivityMedicalRecords> {
    public PresenterMedicalRecords(IMedicalRecordsActy view, ActivityMedicalRecords activity) {
        super(view, activity);
    }
}
