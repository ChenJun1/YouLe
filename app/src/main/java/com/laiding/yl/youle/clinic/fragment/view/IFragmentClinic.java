package com.laiding.yl.youle.clinic.fragment.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.clinic.entity.ClinicBean;

import java.util.List;

/**
 * Created by JunChen on 2018/1/17.
 * Remarks
 */

public interface IFragmentClinic extends IBaseView {
    void showResult(ClinicBean clinicBean);

    int getPage();

    String getName();

    String getInfo();

    String getGrade();

    String getService();

    int getAddress();

}
