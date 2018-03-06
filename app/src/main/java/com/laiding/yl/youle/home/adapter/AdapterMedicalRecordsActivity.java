package com.laiding.yl.youle.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.home.entity.MedicalRecordsBean;

import java.util.List;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks 诊疗记录
 */

public class AdapterMedicalRecordsActivity extends BaseQuickAdapter<MedicalRecordsBean,BaseViewHolder> {
    public AdapterMedicalRecordsActivity(@Nullable List<MedicalRecordsBean> data) {
        super(R.layout.item_activity_medical_records,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MedicalRecordsBean item) {
        helper.setText(R.id.time_tv, item.getTime());
        helper.setText(R.id.r_project_tv, item.getR_project());
    }
}
