package com.laiding.yl.youle.home.presenter;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObservable;
import com.laiding.yl.mvprxretrofitlibrary.http.observer.HttpRxObserver;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequest;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.api.ApiUtlis;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.home.activty.ActivityAddMedicalRecords;
import com.laiding.yl.youle.home.activty.view.IAddMedicalRecordsActy;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.mine.entity.UserInfo;
import com.laiding.yl.youle.utils.MConstant;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogEditSureCancel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.DatePicker;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks 添加诊疗记录
 */

public class PresenterAddMedicalRecords extends MyBasePresenter<IAddMedicalRecordsActy, ActivityAddMedicalRecords> {
    private static final String TAG = "PresenterAddMedicalReco";

    public PresenterAddMedicalRecords(IAddMedicalRecordsActy view, ActivityAddMedicalRecords activity) {
        super(view, activity);
    }

    /**
     * 诊疗记录添加
     *
     * @param
     * @param
     */
    public void requestHttp() {
        final Map<String, RequestBody> request = new HashMap<>();
        List<MultipartBody.Part> parts = MConstant.filesToMultipartBodyParts(getView().getFileList());

        RequestBody r_project = RequestBody.create(MediaType.parse("text/plain"), getView().getMedicalTitle());
        RequestBody r_hospital = RequestBody.create(MediaType.parse("text/plain"), getView().getHospital());
        RequestBody time = RequestBody.create(MediaType.parse("text/plain"), getView().getTime());
        RequestBody r_content = RequestBody.create(MediaType.parse("text/plain"), getView().getRemarkes());

        request.put("r_project", r_project);
        request.put("r_hospital", r_hospital);
        request.put("time", time);
        request.put("r_content", r_content);

        HttpRxObserver httpRxObserver = new HttpRxObserver<HttpResponse>(TAG, getView()) {
            @Override
            protected void onStart(Disposable d) {

            }

            @Override
            protected void onError(ApiException e) {

            }

            @Override
            protected void onSuccess(HttpResponse response) {
                if (response.isSuccess()) {
                    RxToast.success(response.getMsg());
                }
            }
        };
        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
        new HttpRxObservable().getObservable(ApiUtlis.getHomeApi().getAddRecord(request,parts), getActivity(), ActivityEvent.STOP).subscribe(httpRxObserver);
    }


    /**
     * 时间选择
     */
    public void showDialogTime() {
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
        picker.setSelectedItem(mYear, mMonth, mDay);
        picker.setOnDatePickListener((DatePicker.OnYearMonthDayPickListener) (year, month, day) -> getActivity().setTime(year + "-" + month + "-" + day));
        picker.show();
    }

    /**
     * 医院选择
     */
    public void showDialogHospital() {
        final RxDialogEditSureCancel hospitalDialog = new RxDialogEditSureCancel(getActivity());
        hospitalDialog.getEditText().setHint("医院");
        hospitalDialog.getEditText().setText(getView().getHospital());
        hospitalDialog.getEditText().setSelection(getView().getHospital().length());
        hospitalDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        hospitalDialog.getSureView().setOnClickListener(v -> {
            String hospital = hospitalDialog.getEditText().getText().toString();
            getView().setHospital(hospital);
            hospitalDialog.cancel();
        });
        hospitalDialog.getCancelView().setOnClickListener(v -> hospitalDialog.cancel());
        hospitalDialog.show();
    }
}
