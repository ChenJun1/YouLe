package com.laiding.yl.youle.information.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.information.entity.CommentListBean;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/3/8.
 * Remarks
 */

public class AdapterInformationDetail extends BaseQuickAdapter<CommentListBean.MessageInfoBean, BaseViewHolder>  {

    public AdapterInformationDetail(@Nullable List<CommentListBean.MessageInfoBean> data) {
        super(R.layout.item_information_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentListBean.MessageInfoBean item) {
        helper.setText(R.id.u_nname_tv, item.getU_nname());
        helper.setText(R.id.m_message_tv, item.getM_message());
        helper.setText(R.id.m_time_tv,MConstant.TimeStamp2Date(item.getTime(),""));
        GlideImageView glideImageView= helper.getView(R.id.photo_giv);
        glideImageView.loadCircleImage(item.getPhoto(), R.mipmap.ic_launcher_round);
    }
}
