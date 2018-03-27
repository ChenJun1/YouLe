package com.laiding.yl.youle.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.mine.entity.CommonToolBean;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/3/21.
 * Remarks
 */

public class AdapterCommonTool extends BaseQuickAdapter<CommonToolBean,BaseViewHolder> {
    public AdapterCommonTool(@Nullable List<CommonToolBean> data) {
        super(R.layout.item_commnon_tool, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonToolBean item) {
        if(item!=null) {
            GlideImageView view = helper.getView(R.id.pic_gliv);
            view.loadImage(item.getFile(), R.mipmap.icon_qidai);
            helper.setText(R.id.text_tv, item.getA_title());
        }
    }
}
