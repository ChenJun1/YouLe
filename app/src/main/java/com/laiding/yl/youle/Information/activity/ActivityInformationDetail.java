package com.laiding.yl.youle.Information.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.youle.Information.activity.view.IInformationDetail;
import com.laiding.yl.youle.Information.adapter.AdapterInformationDetail;
import com.laiding.yl.youle.Information.entity.CommentBean;
import com.laiding.yl.youle.Information.entity.CommentListBean;
import com.laiding.yl.youle.Information.entity.InformationDetailBean;
import com.laiding.yl.youle.Information.presenter.PresenterInformationDetail;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks 资讯详情
 */

public class ActivityInformationDetail extends MyBaseActivity implements IInformationDetail {
    public static final String NID = "NID";
    public static final int PAGE_SIZE = 10;
    @BindView(R.id.iv_bar_right)
    GlideImageView mIvBarRight;
    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;
    @BindView(R.id.home_rl)
    RecyclerView mHomeRl;
    @BindView(R.id.content_et)
    EditText mContentEt;
    @BindView(R.id.submit_bt)
    Button mSubmitBt;

    TextView mTvInformationTitle;
    GlideImageView mPhotoGiv;
    TextView mUNnameTv;
    TextView mTimeTv;
    GlideImageView mFileGiv;
    TextView mNContentTv;
    TextView mTotalNumberTv;

    public static void start(Context context, int nid) {
        Intent starter = new Intent(context, ActivityInformationDetail.class);
        starter.putExtra(NID, nid);
        context.startActivity(starter);
    }

    private AdapterInformationDetail adapter;
    private int nid = -1;
    private PresenterInformationDetail present = new PresenterInformationDetail(this, this);
    private View notDataView;
    private List<CommentListBean.MessageInfoBean> list = new ArrayList<>();
    private int page = 1;
    private boolean isRefresh = true;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_information_detail;
    }

    @Override
    protected void init() {
        setTitle("资讯详情");
        mIvBarRight.loadLocalImage(R.mipmap.icon_zaixiankefu, R.mipmap.icon_zaixiankefu);
        mIvBarRight.setVisibility(View.VISIBLE);
        isBack(true);
        initAdapter();
        addHeadView();
        present.requestInforMationDetail();
        present.requestCommentList();
    }

    /**
     * 添加头部
     */
    private void addHeadView() {
        View head = getLayoutInflater().inflate(R.layout.include_information_detail, (ViewGroup) mHomeRl.getParent(), false);
        mTvInformationTitle=head.findViewById(R.id.tv_information_title);
        mPhotoGiv=head.findViewById(R.id.photo_giv);
        mUNnameTv=head.findViewById(R.id.u_nname_tv);
        mTimeTv=head.findViewById(R.id.time_tv);
        mFileGiv=head.findViewById(R.id.file_giv);
        mNContentTv=head.findViewById(R.id.n_content_tv);
        mTotalNumberTv=head.findViewById(R.id.total_number_tv);
        adapter.addHeaderView(head);
    }

    /**
     * 初始化Adapter
     */
    private void initAdapter() {
        mHomeRl.setLayoutManager(new LinearLayoutManager(mContext));
        notDataView = getLayoutInflater().inflate(R.layout.empty_text_view, (ViewGroup) mHomeRl.getParent(), false);
        TextView textView = notDataView.findViewById(R.id.content_tv);
        textView.setText("暂无评论~~");
        adapter = new AdapterInformationDetail(list);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> loadMore(), mHomeRl);
        mHomeRl.setAdapter(adapter);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        isRefresh = false;
        page++;
        present.requestCommentList();
    }

    @Override
    protected void initBundleData() {
        nid = getIntent().getIntExtra(NID, -1);
    }


    @Override
    public int getNid() {
        return nid;
    }

    @Override
    public void detailResult(InformationDetailBean bean) {
        if (bean == null)
            return;
        mTvInformationTitle.setText(bean.getN_title());
        mPhotoGiv.loadCircleImage(MConstant.AVATARIMGURL + bean.getPhoto(), R.mipmap.ic_launcher_round);
        mUNnameTv.setText(bean.getU_nname());
        mTimeTv.setText(bean.getTime());
        mFileGiv.loadImage(MConstant.IMGURL + bean.getFile(), R.mipmap.ic_launcher);
        mNContentTv.setText(bean.getN_content() == null ? "" : Html.fromHtml(bean.getN_content()));
    }

    @Override
    public void commentResult() {

    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getPostCommetText() {
        return mContentEt.getText().toString();
    }

    @Override
    public void commentListResult(CommentListBean bean) {
        adapter.removeFooterView(notDataView);
        if (bean == null) {
            adapter.loadMoreFail();
            adapter.addFooterView(notDataView);
            return;
        }
        mTotalNumberTv.setText("(" + bean.getTotalNumber() + ")");
        if (isRefresh) {
            adapter.setNewData(bean.getMessageInfo());
        } else {
            if (bean.getMessageInfo() != null && bean.getMessageInfo().size() > 0) {
                adapter.addData(bean.getMessageInfo());
            }
        }
        if (adapter.getData().size() < 1) {
            adapter.addFooterView(notDataView);
        }

        if (bean.getMessageInfo() == null || bean.getMessageInfo().size() < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(isRefresh);
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void postdCommentResult(List<CommentListBean.MessageInfoBean> list) {
        RxToast.success("发表评论成功");
        mContentEt.setText("");
        adapter.setNewData(list);
    }


    @OnClick({R.id.iv_bar_right, R.id.ll_im_bar_right, R.id.submit_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_bar_right:
                break;
            case R.id.ll_im_bar_right:
                break;
            case R.id.submit_bt:
                if(!mContentEt.getText().toString().isEmpty()){
                    present.postedComment();
                }

                break;
        }
    }
}
