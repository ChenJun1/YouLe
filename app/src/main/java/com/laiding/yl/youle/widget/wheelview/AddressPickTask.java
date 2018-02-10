package com.laiding.yl.youle.widget.wheelview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * 获取地址数据并显示地址选择器
 *
 * @author chen
 *
 */
public class AddressPickTask extends AsyncTask<String, Void, ArrayList<Province>> {
    private Activity activity;// TODO: 2018/2/1 StaticFieldLeak
    private ProgressDialog dialog;
    private Callback callback;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private boolean hideProvince = false;
    private boolean hideCounty = false;

    public AddressPickTask(Activity activity) {
        this.activity = activity;
    }

    public void setHideProvince(boolean hideProvince) {
        this.hideProvince = hideProvince;
    }

    public void setHideCounty(boolean hideCounty) {
        this.hideCounty = hideCounty;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }

    @Override
    protected ArrayList<Province> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    selectedProvince = params[0];
                    break;
                case 2:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    break;
                case 3:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    selectedCounty = params[2];
                    break;
                default:
                    break;
            }
        }
        ArrayList<Province> data = new ArrayList<>();
        try {
            String json = ConvertUtils.toString(activity.getAssets().open("city.json"));
            Gson gson=new Gson();
            ArrayList<Province>  dataGson= gson.fromJson(json, new TypeToken<ArrayList<Province>>(){}.getType());
            data.clear();
            data.addAll(dataGson);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onPostExecute(ArrayList<Province> result) {
        dialog.dismiss();
        if (result.size() > 0) {
            AddressPicker picker = new AddressPicker(activity, result);
            picker.setLabelTextColor(0xffF994A6);
            picker.setSubmitTextColor(0xffF994A6);
            picker.setCancelTextColor(0xffF994A6);
            picker.setTopLineColor(0xffF994A6);
            picker.setDividerColor(0xffF994A6);
            picker.setTextColor(0xffF994A6);
            picker.setSubmitTextSize(17);
            picker.setCancelTextSize(17);
            picker.setTextSize(17);
            picker.setHideProvince(hideProvince);
            picker.setHideCounty(hideCounty);
            if (hideCounty) {
                picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);//将屏幕分为3份，省级和地级的比例为1:2
            } else {
                picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
            }
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            picker.setOnAddressPickListener(callback);
            picker.show();
        } else {
            callback.onAddressInitFailed();
        }
    }

    public interface Callback extends AddressPicker.OnAddressPickListener {

        void onAddressInitFailed();

    }

}
