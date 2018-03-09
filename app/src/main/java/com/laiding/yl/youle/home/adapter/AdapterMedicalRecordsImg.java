package com.laiding.yl.youle.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/3/7.
 * Remarks
 */

public class AdapterMedicalRecordsImg extends BaseQuickAdapter<String,BaseViewHolder> {
    public AdapterMedicalRecordsImg(@Nullable List<String> data) {
        super(R.layout.item_medical_records_img, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideImageView view = helper.getView(R.id.giv_pic);
        view.loadImage(MConstant.RECORDSIMGURL + item,R.mipmap.ic_launcher);
    }
}
