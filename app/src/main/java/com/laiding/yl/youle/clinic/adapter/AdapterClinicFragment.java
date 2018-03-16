package com.laiding.yl.youle.clinic.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.clinic.entity.ClinicBean;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.utils.Constant;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/17.
 * Remarks
 */

public class AdapterClinicFragment extends BaseQuickAdapter<ClinicBean.HospitalListBean, BaseViewHolder> {

    public AdapterClinicFragment(@Nullable List<ClinicBean.HospitalListBean> data) {
        super(R.layout.item_fragment_clinic,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClinicBean.HospitalListBean item) {
        helper.setText(R.id.clinic_name_tv, item.getH_name());
        helper.setText(R.id.content_tv, Html.fromHtml(item.getH_info()==null?"":item.getH_info()));
        GlideImageView view = helper.getView(R.id.iv_icon);
        view.loadCircleImage(item.getImg(), R.mipmap.ic_launcher_round);
    }
}
