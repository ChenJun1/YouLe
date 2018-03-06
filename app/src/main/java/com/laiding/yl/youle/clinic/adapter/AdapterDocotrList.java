package com.laiding.yl.youle.clinic.adapter;

import android.support.annotation.Nullable;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.clinic.entity.ClinicDetailBean;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/31.
 * Remarks
 */

public class AdapterDocotrList extends BaseQuickAdapter<ClinicDetailBean.DoctorListBean,BaseViewHolder> {
    public AdapterDocotrList(@Nullable List<ClinicDetailBean.DoctorListBean> data) {
        super(R.layout.item_doctor, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClinicDetailBean.DoctorListBean item) {
        GlideImageView imageView= helper.getView(R.id.tv_avatar);
        imageView.loadCircleImage(MConstant.IMGURL+item.getD_file(), R.mipmap.ic_launcher_round);
        helper.setText(R.id.tv_d_name, item.getD_name());
        helper.setText(R.id.tv_d_room, item.getD_room());
        helper.setText(R.id.tv_d_duties, item.getD_duties());
        helper.setText(R.id.tv_d_info, Html.fromHtml(item.getD_info()==null?"":item.getD_info()));
    }
}
