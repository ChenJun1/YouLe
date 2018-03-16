package com.laiding.yl.youle.login.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.home.activty.ActivityHome;
import com.laiding.yl.youle.login.activity.view.ILoginView;
import com.laiding.yl.youle.login.entity.User;
import com.laiding.yl.youle.login.entity.UserBean;
import com.laiding.yl.youle.login.presenter.PresenterLogin;
import com.laiding.yl.youle.widget.RLoadingDialog;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.RxAnimationTool;
import com.vondear.rxtools.RxRegTool;
import com.vondear.rxtools.RxTool;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/3.
 * 手机登陆
 */

public class ActivityPhoneLogin extends MyBaseActivity implements ILoginView {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityPhoneLogin.class);
        context.startActivity(starter);
    }

    @BindView(R.id.giv_agree)
    GlideImageView mGivAgree;
    @BindView(R.id.et_verification_code)
    EditText mEtVerificationCode;
    @BindView(R.id.iv_weixin)
    ImageView mIvWeixin;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_weibo)
    ImageView mIvWeibo;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.countDown)
    TextView countDown;
    @BindView(R.id.pass_login_tv)
    TextView passLoginTv;
    @BindView(R.id.logo_giv)
    GlideImageView loginIv;
    @BindView(R.id.et_mobile)
    EditText phoneEt;
    @BindView(R.id.iv_clean_phone)
    ImageView mIvCleanPhone;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.root)
    RelativeLayout mRoot;
    @BindView(R.id.content)
    LinearLayout mContent;

    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private int height = 0;

    private boolean isAgree = true;  //阅读同意check
    private PresenterLogin presenter = new PresenterLogin(this, this);

    @Override
    protected int getContentViewId() {
        return R.layout.activity_phone_login;
    }

    @Override
    protected void init() {
        initEvent();
        initView();
    }

    private void initEvent() {
        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvCleanPhone.getVisibility() == View.GONE) {
                    mIvCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvCleanPhone.setVisibility(View.GONE);
                }
            }
        });

        /**
         * 禁止键盘弹起的时候可以滚动
         */
        mScrollView.setOnTouchListener((v, event) -> true);
        mScrollView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
          /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
          现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
            if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                int dist = mContent.getBottom() - bottom;
                dist = dist / 3; //向上弹起的高度3分1刚刚好
                if (dist > 0) {
                    ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -dist);
                    mAnimatorTranslateY.setDuration(300);
                    mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                    mAnimatorTranslateY.start();
                    RxAnimationTool.zoomIn(loginIv, scale, dist);
                }
//                    mService.setVisibility(View.INVISIBLE);

            } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                if ((mContent.getBottom() - oldBottom) > 0) {
                    ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", mContent.getTranslationY(), 0);
                    mAnimatorTranslateY.setDuration(300);
                    mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                    mAnimatorTranslateY.start();
                    //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                    RxAnimationTool.zoomOut(loginIv, scale);
                }
//                    mService.setVisibility(View.VISIBLE);
            }
        });

    }

    private void initView() {
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3
    }

    @Override
    protected void initBundleData() {

    }


    @Override
    public void showResult() {
        toChat();
    }

    @Override
    public void toChat() {
        ActivityHome.start(this);
        this.finish();
    }

    @Override
    public String getPhone() {
        return phoneEt.getText() + "";
    }

    @Override
    public String getVerificationCode() {
        return mEtVerificationCode.getText() + "";
    }

    /**
     * 检查正确手机号
     */
    private boolean checkPhone() {

        if (getPhone().isEmpty()) {
            RxToast.warning("请输入手机号");
            return false;
        }

        if (RxRegTool.isMobile(String.valueOf(phoneEt.getText()))) {
            RxTool.countDown(countDown, 60000, 1000, "重新获取验证码");
            return true;
        } else {
            RxToast.warning("请输入正确手机号");
            return false;
        }

    }

    /**
     * 检查正确手机号和验证码
     */
    private boolean checkPhoneAndCode() {

        if (getPhone().isEmpty()) {
            RxToast.warning("请输入手机号");
            return false;
        }

        if (getVerificationCode().isEmpty()) {
            RxToast.warning("请输入验证码");
            return false;
        }

        if(!isAgree){
            RxToast.warning("请阅读协议");
            return isAgree;
        }

        if (RxRegTool.isMobile(String.valueOf(phoneEt.getText()))) {
            RxTool.countDown(countDown, 60000, 1000, "重新获取验证码");
            return true;
        } else {
            RxToast.warning("请输入正确手机号");
            return false;
        }

    }


    @OnClick({R.id.giv_agree, R.id.iv_clean_phone, R.id.countDown, R.id.login_bt, R.id.pass_login_tv, R.id.iv_weixin, R.id.iv_qq, R.id.iv_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clean_phone:
                phoneEt.setText("");
                break;
            case R.id.countDown:

//                try {
//                    EMClient.getInstance().createAccount("8002","123456");
//                    LogUtils.e("注册成功");
//                } catch (HyphenateException e) {
//                    e.printStackTrace();
//                    LogUtils.e("注册失败");
//                }
                //获取验证码
                if (checkPhone()) {
                    presenter.getVerificationCode();
                }
                break;
            case R.id.login_bt:
                if(checkPhoneAndCode())
                presenter.login();
                break;
            case R.id.pass_login_tv:
                ActivityPassLogin.start(this);
                break;
            case R.id.iv_weixin:
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weibo:
                break;
            case R.id.giv_agree:
                if (isAgree) {
                    mGivAgree.loadLocalImage(R.mipmap.denglu_icon_fuxuankuang, R.mipmap.denglu_icon_fuxuankuang);
                    isAgree = false;
                } else {
                    mGivAgree.loadLocalImage(R.mipmap.icon_tkxx, R.mipmap.icon_tkxx);
                    isAgree = true;
                }
                break;
        }
    }

}
