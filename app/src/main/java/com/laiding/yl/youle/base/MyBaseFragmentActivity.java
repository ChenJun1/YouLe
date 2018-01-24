package com.laiding.yl.youle.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.mvprxretrofitlibrary.base.BaseFragmentActivity;
import com.laiding.yl.youle.R;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.RxBarTool;

import org.w3c.dom.Text;

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
}
