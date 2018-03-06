package com.laiding.yl.youle.home.activty.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks
 */

public interface IAddMedicalRecordsActy extends IBaseView {
    void setHospital(String hospital);

    String getHospital();

    void setTime(String time);

    String getTime();

    String getMedicalTitle();

    String getRemarkes();

    List<File> getFileList();
}
