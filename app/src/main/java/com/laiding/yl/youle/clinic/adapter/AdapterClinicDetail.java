package com.laiding.yl.youle.clinic.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.clinic.entity.ClinicDetailBean;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/29.
 * Remarks
 */

public class AdapterClinicDetail extends BaseQuickAdapter<ClinicDetailBean.DoctorListBean, BaseViewHolder> {

    public AdapterClinicDetail(@Nullable List<ClinicDetailBean.DoctorListBean> data) {
        super(R.layout.item_clinic_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClinicDetailBean.DoctorListBean item) {
        GlideImageView view = helper.getView(R.id.giv_pic);
        view.loadCircleImage(MConstant.IMGURL+item.getD_file(), R.mipmap.ic_launcher_round);
        helper.setText(R.id.tv_name, item.getD_name());
        helper.setText(R.id.tv_department, item.getD_room());
        helper.setText(R.id.tv_function, item.getD_duties());
    }
}
