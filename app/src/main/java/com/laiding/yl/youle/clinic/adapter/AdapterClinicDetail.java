package com.laiding.yl.youle.clinic.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/29.
 * Remarks
 */

public class AdapterClinicDetail extends BaseQuickAdapter<ForumPostsBean, BaseViewHolder> {

    public AdapterClinicDetail(@Nullable List<ForumPostsBean> data) {
        super(R.layout.item_clinic_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ForumPostsBean item) {
        GlideImageView view = helper.getView(R.id.giv_pic);
        view.loadLocalCircleImage(R.drawable.beijing, R.mipmap.ic_launcher);
    }
}
