package com.laiding.yl.youle.mine.presenter;

import android.view.Gravity;
import android.view.View;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.mine.activity.ActivityPersonalInformation;
import com.laiding.yl.youle.mine.activity.view.IPersnonalInformation;
import com.laiding.yl.youle.widget.wheelview.AddressPickTask;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxTimeTool;
import com.vondear.rxtools.view.dialog.RxDialogEditSureCancel;
import com.vondear.rxtools.view.dialog.RxDialogWheelYearMonthDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.SinglePicker;

import static com.vondear.rxtools.view.RxToast.showToast;

/**
 * Created by JunChen on 2018/1/31.
 * Remarks
 */

public class PresenterPersonalInformation extends MyBasePresenter<IPersnonalInformation, ActivityPersonalInformation> {

    public PresenterPersonalInformation(IPersnonalInformation view, ActivityPersonalInformation activity) {
        super(view, activity);
    }

    /**
     * 昵称
     */
    public void showDialogNikeName() {
        final RxDialogEditSureCancel nikeNameDialog = new RxDialogEditSureCancel(getActivity());
        nikeNameDialog.getEditText().setHint("昵称");
        nikeNameDialog.getEditText().setText(getView().getNikeName());
        nikeNameDialog.getEditText().setSelection(getView().getNikeName().length());
        nikeNameDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        nikeNameDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence nikeName = nikeNameDialog.getEditText().getText();
                getView().setNikeName(nikeName);
                nikeNameDialog.cancel();
            }
        });
        nikeNameDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nikeNameDialog.cancel();
            }
        });
        nikeNameDialog.show();
    }


    /**
     * 性别
     */
    public void showDialogGender() {
        List<String> data = new ArrayList<>();
        data.add("男");
        data.add("女");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(), data);
        picker.setLabelTextColor(0xffF994A6);
        picker.setSubmitTextColor(0xffF994A6);
        picker.setCancelTextColor(0xffF994A6);
        picker.setTopLineColor(0xffF994A6);
        picker.setDividerColor(0xffF994A6);
        picker.setTextColor(0xffF994A6);
        picker.setSubmitTextSize(17);
        picker.setCancelTextSize(17);
        picker.setTextSize(17);
        picker.setSelectedItem(getView().getGender().toString());
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                getView().setGender(item);
            }
        });
        picker.show();
    }

    /**
     * 生日
     */
    public void showDialigBirthday() {
        Calendar c = Calendar.getInstance();//
        final int mYear = c.get(Calendar.YEAR); // 获取当前年份
        final int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        DatePicker picker = new DatePicker(getActivity(), DatePicker.YEAR_MONTH);
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
        picker.setRangeEnd(mYear, mMonth, 1);
        picker.setSelectedItem(mYear, mMonth);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                int age = getAge(mYear, mMonth, RxDataTool.stringToInt(year), RxDataTool.stringToInt(month));
                getView().setBirthday(age + "");
            }
        });
        picker.show();
    }


    /**
     * 真实姓名
     */
    public void showDialogName() {
        final RxDialogEditSureCancel NameDialog = new RxDialogEditSureCancel(getActivity());
        NameDialog.getEditText().setHint("姓名");
        NameDialog.getEditText().setText(getView().getName());
        NameDialog.getEditText().setSelection(getView().getName().length());
        NameDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        NameDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence Name = NameDialog.getEditText().getText();
                getView().setName(Name);
                NameDialog.cancel();
            }
        });
        NameDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameDialog.cancel();
            }
        });
        NameDialog.show();
    }

    /**
     * 所在地
     */
    public void showDialogLocation() {
        AddressPickTask task = new AddressPickTask(getActivity());
        task.setHideCounty(true);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                showToast("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                getView().setProvinceLocation(province.getAreaName());
                getView().setAreaLocation(city.getAreaName());
            }
        });
        task.execute(getView().getProvinceLocation().toString(), getView().getAreaLocation().toString());
    }

    /**
     * 详细地址
     */
    public void showDialogDetailLocation() {
        final RxDialogEditSureCancel detailLocationDialog = new RxDialogEditSureCancel(getActivity());
        detailLocationDialog.getEditText().setHint("详细地址");
        detailLocationDialog.getEditText().setText(getView().getDetailLocation());
        detailLocationDialog.getEditText().setSelection(getView().getDetailLocation().length());
        detailLocationDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        detailLocationDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence Name = detailLocationDialog.getEditText().getText();
                getView().setDetailLocation(Name);
                detailLocationDialog.cancel();
            }
        });
        detailLocationDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailLocationDialog.cancel();
            }
        });
        detailLocationDialog.show();
    }

    /**
     * 邮政编码
     */
    public void showDialogPostal() {
        final RxDialogEditSureCancel postalDialog = new RxDialogEditSureCancel(getActivity());
        postalDialog.getEditText().setHint("详细地址");
        postalDialog.getEditText().setText(getView().getPostal());
        postalDialog.getEditText().setSelection(getView().getPostal().length());
        postalDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        postalDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence postal = postalDialog.getEditText().getText();
                getView().setPostal(postal);
                postalDialog.cancel();
            }
        });
        postalDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postalDialog.cancel();
            }
        });
        postalDialog.show();
    }

    /**
     * 邮箱
     */
    public void showDialogEmail() {
        final RxDialogEditSureCancel emailDialog = new RxDialogEditSureCancel(getActivity());
        emailDialog.getEditText().setHint("邮箱");
        emailDialog.getEditText().setText(getView().getEmail());
        emailDialog.getEditText().setSelection(getView().getEmail().length());
        emailDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        emailDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence email = emailDialog.getEditText().getText();
                getView().setEmail(email);
                emailDialog.cancel();
            }
        });
        emailDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailDialog.cancel();
            }
        });
        emailDialog.show();
    }

    private int getAge(int nowYear, int nowMonth, int inputYeay, int inputMonth) {
        int age = nowYear - inputYeay > 0 ? nowYear - inputYeay : 0;

        if (nowMonth >= inputMonth) {
            return age;
        } else {
            return --age > 0 ? age : 0;
        }
    }
}
