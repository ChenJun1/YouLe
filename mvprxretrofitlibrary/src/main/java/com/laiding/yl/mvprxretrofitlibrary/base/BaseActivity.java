package com.laiding.yl.mvprxretrofitlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.laiding.yl.mvprxretrofitlibrary.listener.LifeCycleListener;
import com.laiding.yl.mvprxretrofitlibrary.listener.ProgressListener;
import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.mvprxretrofitlibrary.widget.RLoadingDialog;
import com.trello.rxlifecycle2.components.RxActivity;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.RxKeyboardTool;
import com.vondear.rxtools.view.RxToast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 基类Activity
 * 备注:所有的Activity都继承自此Activity
 * 1.规范团队开发
 * 2.统一处理Activity所需配置,初始化
 *
 * @author JunChen
 */
public abstract class BaseActivity extends RxActivity implements EasyPermissions.PermissionCallbacks,IBaseView {
    private static final String TAG = "BaseActivity";
    protected Context mContext;
    protected Unbinder unBinder;
    protected RLoadingDialog mLoadingDialog;
    protected TextView mTitle=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mListener != null) {
            mListener.onCreate(savedInstanceState);
        }
        ActivityStackManager.getManager().push(this);
        setContentView(getContentViewId());
//        RxBarTool.setTransparentStatusBar(this);//状态栏透明化
        mContext = this;
        unBinder = ButterKnife.bind(this);
        if (mLoadingDialog == null) {
            mLoadingDialog = new RLoadingDialog(this, true);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
        LogUtils.d(TAG+"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
        LogUtils.d(TAG+"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
        LogUtils.d(TAG+"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
        LogUtils.d(TAG+"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
        LogUtils.d(TAG+"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }
        ActivityStackManager.getManager().remove(this);
        LogUtils.d(TAG+"onDestroy"+this.getClass().getSimpleName());
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
    public LifeCycleListener mListener;

    public void setOnLifeCycleListener(LifeCycleListener listener) {
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

    /**
     *  提示错误信息
     * @param errorMsg
     */
    @Override
    public void showError(String errorMsg) {
        RxToast.error(errorMsg);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //点击空白隐藏键盘
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (v != null && (v instanceof EditText)) {
                int[] l = { 0, 0 };
                v.getLocationInWindow(l);
                int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                        + v.getWidth();
                if (ev.getX() > left && ev.getX() < right
                        && ev.getY() > top && ev.getY() < bottom) {
                    // 点击EditText的事件，忽略它。
                } else {
                    RxKeyboardTool.hideSoftInput(this);
                }
            }

        }
        return super.dispatchTouchEvent(ev);
    }


}
