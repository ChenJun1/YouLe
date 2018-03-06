package com.laiding.yl.youle.home.activty.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.home.entity.MedicalRecordsBean;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks
 */

public interface IMedicalRecordsDetailActy extends IBaseView{
    void showResult(MedicalRecordsBean bean);

    int getRID();
}
