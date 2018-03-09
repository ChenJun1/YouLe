package com.laiding.yl.youle.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.laiding.yl.mvprxretrofitlibrary.base.BaseFragmentActivity;
import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.dao.UserInfoManager;
import com.laiding.yl.youle.login.activity.ActivityPhoneLogin;
import com.laiding.yl.youle.login.entity.User;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import static com.vondear.rxtools.RxBarTool.getStatusBarHeight;

/**
 * Created by JunChen on 2018/1/18.
 * Remarks
 */

public abstract class MyBaseFragmentActivity extends BaseFragmentActivity {
    protected TextView mTitle;
    protected LinearLayout mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RxBarTool.setTransparentStatusBar(this);//状态栏透明化
        initBar();
        initWindows();
        initBundleData();
        init();
    }

    @SuppressLint("WrongViewCast")
    public void initBar() {
        mTitle = findViewById(R.id.tv_title);
        mIvBack =  findViewById(R.id.ll_back);
    }

    /**
     * 返回键
     * @param back
     */
    protected void isBack(boolean back){
        if(null==mIvBack)
            return;
        if(back){
            mIvBack.setVisibility(View.VISIBLE);
            mIvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * 头部右侧文字
     * @param
     */
    protected void setRightText(TextView mText,String content){
        mText.setText(content);
        mText.setVisibility(View.VISIBLE);
    }

    /**
     * 头部右侧图标
     * @param
     */
    protected void setRightImag(GlideImageView mBarRightImgView,@DrawableRes int resId){
        mBarRightImgView.loadLocalImage(resId, resId);
        mBarRightImgView.setVisibility(View.VISIBLE);
    }

    /**
     * 标题
     * @param title
     */
    protected void setTitle(String title){
        if(null==mTitle)
            return;
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(title);
    }

    /**
     * 设置状态栏
     */
    private void initWindows() {
        Window window = getWindow();
        int color = getResources().getColor(R.color.colorPrimary);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(color);
            //设置导航栏颜色
            window.setNavigationBarColor(color);
            ViewGroup contentView = ((ViewGroup) findViewById(android.R.id.content));
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置contentview为fitsSystemWindows
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
            //给statusbar着色
            View view = new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this)));
            view.setBackgroundColor(color);
            contentView.addView(view);
        }
    }
    /**
     *  token 过期重新登录
     * @param erreMsg
     */
    @Override
    public void isTokenExpired(String erreMsg) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mContext);//提示弹窗
        rxDialogSureCancel.setContent(erreMsg);
        rxDialogSureCancel.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        rxDialogSureCancel.getSureView().setOnClickListener(v ->{
            ActivityStackManager.getManager().finishAllActivity();
            ActivityPhoneLogin.start(mContext);

            EMClient.getInstance().logout(true, new EMCallBack() {

                @Override
                public void onSuccess() {
                    // TODO Auto-generated method stub
                    LogUtils.e("推出成功");
                }

                @Override
                public void onProgress(int progress, String status) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onError(int code, String message) {
                    // TODO Auto-generated method stub
                    LogUtils.e("推出失败");
                }
            });
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(v -> rxDialogSureCancel.cancel());
        rxDialogSureCancel.show();
    }
    @Override
    public boolean isLogin() {
        User user = UserInfoManager.getUserInfo();
        return !user.getToken().isEmpty();
    }
}
