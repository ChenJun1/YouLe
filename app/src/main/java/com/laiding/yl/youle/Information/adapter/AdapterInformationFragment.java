package com.laiding.yl.youle.Information.adapter;

import android.support.annotation.Nullable;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxTimeTool;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks
 */

public class AdapterInformationFragment extends BaseQuickAdapter<CommunityBean, BaseViewHolder> {
    public AdapterInformationFragment(@Nullable List<CommunityBean> data) {
        super( R.layout.item_fragment_information,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityBean item) {
        helper.setText(R.id.tv_content, Html.fromHtml(item.getN_content()==null?"":item.getN_content()));
        GlideImageView imageView = helper.getView(R.id.gridView);
        imageView.loadImage(MConstant.IMGURL+item.getFile(),R.mipmap.ic_launcher);

        Date date1 = RxTimeTool.string2Date(item.getTime()==null?"": item.getTime());
        helper.setText(R.id.tv_time,RxTimeTool.simpleDateFormat("MM-dd", date1));
    }
}
