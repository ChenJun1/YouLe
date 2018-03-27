package com.laiding.yl.youle.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.mine.activity.view.ICommnoTool;
import com.laiding.yl.youle.mine.adapter.AdapterCommonTool;
import com.laiding.yl.youle.mine.entity.CommonToolBean;
import com.laiding.yl.youle.mine.presenter.PresenterCommonTool;
import com.laiding.yl.youle.webview.ActivityWebView;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by JunChen on 2018/3/21.
 * Remarks 常用工具
 */

public class ActivityCommonTool extends MyBaseActivity implements ICommnoTool{
    private static final String TAG = "ActivityCommonTool";
    @BindView(R.id.rcy_view)
    RecyclerView mRcyView;


    private AdapterCommonTool adapter;
    private List<CommonToolBean> mList = new ArrayList<>();
    private PresenterCommonTool mPresenterCommonTool=new PresenterCommonTool(this,this);
    private View notDataView;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityCommonTool.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_common_tool;
    }

    @Override
    protected void init() {
        setTitle("常用工具");
        isBack(true);
        initAdapter();
        mPresenterCommonTool.requestCommnoTool();
    }

    private void initAdapter() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_text_view, (ViewGroup) mRcyView.getParent(), false);
        mRcyView.setLayoutManager(new GridLayoutManager(mContext,3));
        adapter = new AdapterCommonTool(mList);
        mRcyView.setAdapter(adapter);
        mRcyView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position!=adapter.getItemCount()-1) {
                    CommonToolBean item = (CommonToolBean) adapter.getItem(position);
                    ActivityWebView.start(mContext, item != null ? item.getA_link() : null, item != null ? item.getA_title() : null);
                }else{
                    RxToast.info("敬请期待");
                }
            }
        });
    }

    @Override
    protected void initBundleData() {
    }

    @Override
    public void onSuccess(List<CommonToolBean> list) {
        if(list==null||list.size()<1){
            adapter.setEmptyView(notDataView);
        }else{
            adapter.setNewData(list);
        }
    }
}
