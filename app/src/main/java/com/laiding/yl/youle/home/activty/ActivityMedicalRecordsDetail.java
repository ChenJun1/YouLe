package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.home.activty.view.IMedicalRecordsDetailActy;
import com.laiding.yl.youle.home.adapter.AdapterMedicalRecordsImg;
import com.laiding.yl.youle.home.entity.MedicalRecordsBean;
import com.laiding.yl.youle.home.presenter.PresenterMedicalRecordsDetail;
import com.laiding.yl.youle.utils.MConstant;
import com.laiding.yl.youle.widget.MyItemDecoration;
import com.vondear.rxtools.view.dialog.RxDialogScaleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks 诊疗详情
 */

public class ActivityMedicalRecordsDetail extends MyBaseActivity implements IMedicalRecordsDetailActy {



    public static void start(Context context, int rid) {
        Intent starter = new Intent(context, ActivityMedicalRecordsDetail.class);
        starter.putExtra("ID", rid);
        context.startActivity(starter);
    }

    @BindView(R.id.medical_rl)
    RecyclerView mMedicalRl;
    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;
    @BindView(R.id.et_mr_title)
    EditText mEtMrTitle;
    @BindView(R.id.tv_mr_time)
    TextView mTvMrTime;
    @BindView(R.id.tv_hospital)
    TextView mTvHospital;
    @BindView(R.id.et_content)
    EditText mEtContent;

    private int Rid = 0;
    private PresenterMedicalRecordsDetail presenter = new PresenterMedicalRecordsDetail(this, this);
    private AdapterMedicalRecordsImg adpter;
    private List<String> mStringList=new ArrayList<>();
    @Override
    protected int getContentViewId() {
        return R.layout.activity_medical_records_detai;
    }

    @Override
    protected void init() {
        setTitle("详情");
        isBack(true);
        initRlView();
        presenter.requestHttp();
    }

    private void initRlView() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,3);
        mMedicalRl.setLayoutManager(gridLayoutManager);
        mMedicalRl.addItemDecoration(new MyItemDecoration());
        adpter=new AdapterMedicalRecordsImg(mStringList);
        mMedicalRl.setAdapter(adpter);

        mMedicalRl.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                String item = (String) adapter.getItem(position);
                final  RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mContext);
                //加载BitMap到视图
                Glide.with(mContext).asBitmap().load(MConstant.RECORDSIMGURL+item).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        rxDialogScaleView.setImageBitmap(resource);
                    }
                });
                rxDialogScaleView.show();
            }
        });
    }

    @Override
    protected void initBundleData() {
        Rid = getIntent().getIntExtra("ID", 0);
    }

    @Override
    public void showResult(MedicalRecordsBean bean) {
        if (bean == null)
            return;
        mEtMrTitle.setText(bean.getR_project());
        mTvMrTime.setText(bean.getTime());
        mTvHospital.setText(bean.getR_hospital());
        mEtContent.setText(bean.getR_content());
        if(bean.getImg()!=null&&!bean.getImg().isEmpty()){
            mStringList.clear();
            String imgs = bean.getImg();
            String[] split = imgs.split(",");
            mStringList.addAll(Arrays.asList(split));
            adpter.setNewData(mStringList);
        }
    }

    @Override
    public int getRID() {
        return Rid;
    }

}
