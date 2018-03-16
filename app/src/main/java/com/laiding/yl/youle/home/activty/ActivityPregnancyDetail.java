package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jorge.circlelibrary.ImageCycleView;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragmentActivity;
import com.laiding.yl.youle.home.activty.view.IPregnancyDetail;
import com.laiding.yl.youle.home.entity.PregnancyDetailBean;
import com.laiding.yl.youle.home.presenter.PresenterPregnancyDetail;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.sunfusheng.glideimageview.GlideImageView;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/2/23.
 * Remarks 备孕检查详情
 */

public class ActivityPregnancyDetail extends MyBaseFragmentActivity implements IPregnancyDetail {
    static final String PID = "PID";
    @BindView(R.id.iv_bar_right)
    GlideImageView mIvBarRight;
    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;
    @BindView(R.id.cycleView)
    ImageCycleView mCycleView;
    @BindView(R.id.p_title_tv)
    TextView mPTitleTv;
    @BindView(R.id.p_price_tv)
    TextView mPPriceTv;
    @BindView(R.id.service_tv)
    TextView mServiceTv;
    @BindView(R.id.service_view)
    View mServiceView;
    @BindView(R.id.reservation_tv)
    TextView mReservationTv;
    @BindView(R.id.reservation_view)
    View mReservationView;
    @BindView(R.id.content_tv)
    TextView mContentTv;
    @BindView(R.id.service_ll)
    LinearLayout mServiceLl;
    @BindView(R.id.reservation_ll)
    LinearLayout mReservationLl;
    @BindView(R.id.ll_call_doctor)
    LinearLayout mLlCallDoctor;

    public static void start(Context context, int pid) {
        Intent starter = new Intent(context, ActivityPregnancyDetail.class);
        starter.putExtra(PID, pid);
        context.startActivity(starter);
    }

    private RichText mRichText;
    private int pid = -1;
    private PresenterPregnancyDetail prsenter = new PresenterPregnancyDetail(this, this);
    private PregnancyDetailBean mPregnancyDetailBean;
    /**
     * 装在数据的集合  文字描述
     */
    ArrayList<String> imageDescList = new ArrayList<>();
    /**
     * 装在数据的集合  图片地址
     */
    ArrayList<String> urlList = new ArrayList<>();
    @Override
    protected int getContentViewId() {
        return R.layout.activity_pregnancy_detail;
    }

    @Override
    protected void init() {
        RichText.initCacheDir(MyApplication.app);
        setTitle("备孕详情");
        isBack(true);
        initView();
        prsenter.requestHttp();
    }
    /**
     * 初始化轮播图
     */
    private void initView() {
        // 是否隐藏底部
        mCycleView.hideBottom(true);
        //轮播类型
        mCycleView.setCycle_T(ImageCycleView.CYCLE_T.CYCLE_VIEW_NORMAL);
    }

    /**
     *  填充轮播图
     * @param picturesList
     */
    private void initCycleViewData(List<String> picturesList) {
        urlList.clear();
        imageDescList.clear();
        if (picturesList == null) {
            imageDescList.add("");
            urlList.add("");
        } else {
            for (String adsPictures : picturesList) {
                imageDescList.add("");
                urlList.add("http://back.51laiding.xyz/photo/"+adsPictures);
            }
        }
        mCycleView.setImageResources(imageDescList, urlList, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                /**在此方法中，显示图片，可以用自己的图片加载库，也可以用本demo中的（Imageloader）*/
                Glide.with(mContext).load(imageURL).into(imageView);
            }

            @Override
            public void onImageClick(int position, View imageView) {
                /**实现点击事件*/
                LogUtils.d("点击" + position);
            }
        });
        mCycleView.startImageCycle();
    }


    @Override
    protected void initBundleData() {
        pid = getIntent().getIntExtra(PID, -1);
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public void SuccessResult(PregnancyDetailBean bean) {
        if (bean == null) {
            return;
        }
        mPregnancyDetailBean = bean;
        mPTitleTv.setText(bean.getP_title());
        mPPriceTv.setText(bean.getP_price());
        if (!bean.getImg().isEmpty()) {
            String[] split = bean.getImg().split(",");
            initCycleViewData(Arrays.asList(split));
        }
        mRichText = RichText.from(mPregnancyDetailBean.getP_content()).cache(CacheType.all).into(mContentTv);

    }

    @OnClick({R.id.ll_im_bar_right, R.id.service_ll, R.id.reservation_ll, R.id.ll_call_doctor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_im_bar_right:
                break;
            case R.id.service_ll:
                mServiceTv.setTextColor(0xff1f1f1f);
                mServiceView.setVisibility(View.VISIBLE);
                mReservationTv.setTextColor(0xff575757);
                mReservationView.setVisibility(View.GONE);
                if (mPregnancyDetailBean != null)
                    mRichText = RichText.from(mPregnancyDetailBean.getP_content()).cache(CacheType.all).into(mContentTv);
                break;
            case R.id.reservation_ll:
                mServiceTv.setTextColor(0xff575757);
                mServiceView.setVisibility(View.GONE);
                mReservationTv.setTextColor(0xff1f1f1f);
                mReservationView.setVisibility(View.VISIBLE);
                if (mPregnancyDetailBean != null)
                    mRichText = RichText.from(mPregnancyDetailBean.getP_bespeak()).cache(CacheType.all).into(mContentTv);
                break;
            case R.id.ll_call_doctor:
                ActivityChat.start(mContext, "19", "","");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRichText.clear();
        RichText.clear(MyApplication.app);
    }
}
