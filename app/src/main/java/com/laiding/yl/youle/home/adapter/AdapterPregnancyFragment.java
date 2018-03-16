package com.laiding.yl.youle.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.home.entity.PregnancyBean;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks  备孕
 */

public class AdapterPregnancyFragment extends BaseQuickAdapter<PregnancyBean, BaseViewHolder> {

    public AdapterPregnancyFragment( @Nullable List<PregnancyBean> data) {
        super(R.layout.item_prepare_for_pregnancy, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PregnancyBean item) {
        helper.setText(R.id.title_tv, item.getP_title());
        helper.setText(R.id.tv_p_price, item.getP_price());
        GlideImageView imageView = helper.getView(R.id.pic_iv);
        imageView.loadImage(item.getFile(),R.mipmap.ic_launcher);
    }
}
