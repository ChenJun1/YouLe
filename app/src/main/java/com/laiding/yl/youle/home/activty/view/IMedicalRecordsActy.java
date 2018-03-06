package com.laiding.yl.youle.home.activty.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.home.entity.MedicalRecordsBean;

import java.util.List;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks 诊疗记录
 */

public interface IMedicalRecordsActy extends IBaseView {
    void showResult(List<MedicalRecordsBean> list);
}
