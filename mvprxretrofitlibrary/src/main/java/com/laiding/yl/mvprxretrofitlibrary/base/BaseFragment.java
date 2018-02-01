package com.laiding.yl.mvprxretrofitlibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laiding.yl.mvprxretrofitlibrary.listener.FrgLifeCycleListener;
import com.laiding.yl.mvprxretrofitlibrary.listener.ProgressListener;
import com.laiding.yl.mvprxretrofitlibrary.widget.RLoadingDialog;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by JunChen on 2018/1/4.
 * Remarks
 */

public abstract class BaseFragment extends RxFragment implements IBaseView,EasyPermissions.PermissionCallbacks{
    private static final String TAG = "BaseFragment";
    protected Unbinder unBinder;
    protected Context mContext;
    protected RLoadingDialog mLoadingDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewId(), container, false);
        if (mLoadingDialog == null) {
            mLoadingDialog = new RLoadingDialog(getActivity(), false);
        }
        mContext = getActivity();
        unBinder = ButterKnife.bind(this, view);
        initBundleData();
        init();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        LogUtils.d(TAG+"onStart");
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.d(TAG+"onResume");
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        LogUtils.d(TAG+"onPause");
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
//        LogUtils.d(TAG+"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mListener != null) {
            mListener.onDestroyView();
        }
//        LogUtils.d(TAG+"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }
//        LogUtils.d(TAG+"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null) {
            mListener.onDetach();
        }
//        LogUtils.d(TAG+"onDetach");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
    }

    /**
     * 获取显示view的xml文件ID
     */
    protected abstract int getContentViewId();

    /**
     * 初始化应用程序，设置一些初始化数据,获取数据等操作
     */
    protected abstract void init();

    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initBundleData();

    /**
     * 回调函数
     */
    public FrgLifeCycleListener mListener;

    public void setOnLifeCycleListener(FrgLifeCycleListener listener) {
        mListener = listener;
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog != null)
            mLoadingDialog.show();
    }

    @Override
    public void closeLoading() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }
}
