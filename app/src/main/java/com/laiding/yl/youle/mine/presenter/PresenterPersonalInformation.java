package com.laiding.yl.youle.mine.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.dao.UserDaoUtil;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.im.db.UserDao;
import com.laiding.yl.youle.login.entity.User;
import com.laiding.yl.youle.mine.activity.ActivityPersonalInformation;
import com.laiding.yl.youle.mine.activity.view.IPersnonalInformation;
import com.laiding.yl.youle.mine.entity.UserInfo;
import com.laiding.yl.youle.widget.wheelview.AddressPickTask;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogEditSureCancel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.SinglePicker;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.vondear.rxtools.view.RxToast.showToast;

/**
 * Created by JunChen on 2018/1/31.
 * Remarks
 */

public class PresenterPersonalInformation extends MyBasePresenter<IPersnonalInformation, ActivityPersonalInformation> {
    private static final String TAG = "PresenterPersonalInform";
    public PresenterPersonalInformation(IPersnonalInformation view, ActivityPersonalInformation activity) {
        super(view, activity);
    }


    /**
     * 获取个人信息
     */
    public void requestUserInfo() {

        final User user = UserInfoManager.getUserInfo();
        final Map<String, Object> request = HttpRequest.getRequest();
        request.put("u_id", user.getU_id());
        request.put("token", user.getToken());

        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<UserInfo>>(TAG,getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse<UserInfo> response) {
                if (response.isSuccess())
                    getView().getUserInfoResult(response.getResult());
            }
        };

        new HttpRxObservable<UserInfo>().getObservable(ApiUtlis.getUserApi().getUserInfo(request),getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }

    /**
     * 修改个人信息
     */
    public void requestUpdateUserInfo() {

        final User user = UserInfoManager.getUserInfo();
        final Map<String, RequestBody> request =new HashMap<>();
        RequestBody uid = RequestBody.create(MediaType.parse("text/plain"), user.getU_id());
        RequestBody nname = RequestBody.create(MediaType.parse("text/plain"), getView().getNikeName().toString());
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), getView().getName().toString());
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), getView().getGender().toString());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), getView().getEmail().toString());
        RequestBody brt = RequestBody.create(MediaType.parse("text/plain"), getView().getBirthday().toString());
        RequestBody region = RequestBody.create(MediaType.parse("text/plain"), getView().getProvinceLocation().toString());
        RequestBody city = RequestBody.create(MediaType.parse("text/plain"), getView().getAreaLocation().toString());
        RequestBody code = RequestBody.create(MediaType.parse("text/plain"), getView().getPostal().toString());
        RequestBody detailLocation = RequestBody.create(MediaType.parse("text/plain"), getView().getDetailLocation().toString());
        RequestBody token = RequestBody.create(MediaType.parse("text/plain"), user.getToken());

        request.put("u_id", uid);
        request.put("u_nname", nname.equals("")?null:nname);
        request.put("u_name", name.equals("")?null:name);
        request.put("u_sex", gender.equals("")?null:gender);
        request.put("u_email", email.equals("")?null:email);
        request.put("u_birthday",brt.equals("")?null:brt);
        request.put("u_region",region.equals("")?null:region);
        request.put("u_city",region.equals("")?null:city);
        request.put("u_code",code.equals("")?null:code);
        request.put("u_address", detailLocation.equals("")?null:detailLocation);
        request.put("token", token);
        MultipartBody.Part imgFile=null;
        if(getView().getAvatar()!=null) {
            RequestBody file = RequestBody.create(MediaType.parse("image/*; charset=utf-8"), getView().getAvatar());
            imgFile=MultipartBody.Part.createFormData("photo","photo.jpg",file);
        }


        HttpRxObserver httpRxObserver=new HttpRxObserver<HttpResponse<UserInfo>>(TAG,getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse<UserInfo> response) {
                if (response.isSuccess()) {
                    getView().getUserInfoResult(response.getResult());
                    RxToast.success("保存成功");
                }
            }
        };

        new HttpRxObservable<UserInfo>().getObservable(ApiUtlis.getUserApi().userUpdate(request,imgFile),getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
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
        nikeNameDialog.getSureView().setOnClickListener(v -> {
            CharSequence nikeName = nikeNameDialog.getEditText().getText();
            getView().setNikeName(nikeName);
            nikeNameDialog.cancel();
        });
        nikeNameDialog.getCancelView().setOnClickListener(v -> nikeNameDialog.cancel());
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
        picker.setOnItemPickListener((index, item) -> getView().setGender(item));
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
        picker.setOnDatePickListener((DatePicker.OnYearMonthPickListener) (year, month) -> {
            int age = getAge(mYear, mMonth, RxDataTool.stringToInt(year), RxDataTool.stringToInt(month));
            getView().setBirthday(age + "");
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
        NameDialog.getSureView().setOnClickListener(v -> {
            CharSequence Name = NameDialog.getEditText().getText();
            getView().setName(Name);
            NameDialog.cancel();
        });
        NameDialog.getCancelView().setOnClickListener(v -> NameDialog.cancel());
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
        detailLocationDialog.getSureView().setOnClickListener(v -> {
            CharSequence Name = detailLocationDialog.getEditText().getText();
            getView().setDetailLocation(Name);
            detailLocationDialog.cancel();
        });
        detailLocationDialog.getCancelView().setOnClickListener(v -> detailLocationDialog.cancel());
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
        postalDialog.getSureView().setOnClickListener(v -> {
            CharSequence postal = postalDialog.getEditText().getText();
            getView().setPostal(postal);
            postalDialog.cancel();
        });
        postalDialog.getCancelView().setOnClickListener(v -> postalDialog.cancel());
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
        emailDialog.getSureView().setOnClickListener(v -> {
            CharSequence email = emailDialog.getEditText().getText();
            getView().setEmail(email);
            emailDialog.cancel();
        });
        emailDialog.getCancelView().setOnClickListener(v -> emailDialog.cancel());
        emailDialog.show();
    }

    /**
     * 计算年龄
     * @param nowYear
     * @param nowMonth
     * @param inputYeay
     * @param inputMonth
     * @return
     */
    private int getAge(int nowYear, int nowMonth, int inputYeay, int inputMonth) {
        int age = nowYear - inputYeay > 0 ? nowYear - inputYeay : 0;

        if (nowMonth >= inputMonth) {
            return age;
        } else {
            return --age > 0 ? age : 0;
        }
    }
}
