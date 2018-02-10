package com.laiding.yl.youle.home.presenter;

import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.home.activty.ActivityAddMedicalRecords;
import com.laiding.yl.youle.home.activty.view.IAddMedicalRecordsActy;
import com.vondear.rxtools.RxDataTool;

import java.util.Calendar;

import cn.qqtheme.framework.picker.DatePicker;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 添加诊疗记录
 */

public class PresenterAddMedicalRecords extends MyBasePresenter<IAddMedicalRecordsActy,ActivityAddMedicalRecords> {

    public PresenterAddMedicalRecords(IAddMedicalRecordsActy view, ActivityAddMedicalRecords activity) {
        super(view, activity);
    }

    /**
     * 时间选择
     */
    public void showDialogTime(){
        Calendar c = Calendar.getInstance();//
        final int mYear = c.get(Calendar.YEAR); // 获取当前年份
        final int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        final int mDay = c.get(Calendar.DATE);// 获取当前日
        DatePicker picker = new DatePicker(getActivity(), DatePicker.YEAR_MONTH_DAY);
        picker.setLabelTextColor(0xffF994A6);
        picker.setSubmitTextColor(0xffF994A6);
        picker.setCancelTextColor(0xffF994A6);
        picker.setTopLineColor(0xffF994A6);
        picker.setDividerColor(0xffF994A6);
        picker.setTextColor(0xffF994A6);
        picker.setSubmitTextSize(17);
        picker.setCancelTextSize(17);
        picker.setTextSize(17);
        picker.setRangeStart(1940, 1, 1);
        picker.setRangeEnd(mYear, mMonth, mDay);
        picker.setSelectedItem(mYear, mMonth,mDay);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                getActivity().setTime(year+"-"+month+"-"+day);
            }

        });
        picker.show();
    }

    /**
     * 医院选择
     */
    public void showDialogHospital(){

    }
}
