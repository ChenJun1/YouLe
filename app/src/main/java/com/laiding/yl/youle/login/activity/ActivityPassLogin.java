package com.laiding.yl.youle.login.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
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
import android.widget.Toast;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.home.activty.ActivityHome;
import com.laiding.yl.youle.login.activity.view.IPassLogin;
import com.laiding.yl.youle.login.presenter.PresenterPassLogin;
import com.vondear.rxtools.RxAnimationTool;
import com.vondear.rxtools.RxKeyboardTool;
import com.vondear.rxtools.RxRegTool;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/4.
 * 密码登陆
 */

public class ActivityPassLogin extends MyBaseActivity implements IPassLogin {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityPassLogin.class);
        context.startActivity(starter);
    }

    @BindView(R.id.logo)
    ImageView mLogo;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.iv_clean_phone)
    ImageView mIvCleanPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.clean_password)
    ImageView mCleanPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView mIvShowPwd;
    @BindView(R.id.btn_login2)
    Button mBtnLogin;
    @BindView(R.id.verification_code_login_tv)
    TextView mVerificationCodeLoginTv;
    @BindView(R.id.get_pass_tv)
    TextView mGetPassTv;
    @BindView(R.id.content)
    LinearLayout mContent;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.root)
    RelativeLayout mRoot;

    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例


    private PresenterPassLogin persenter = new PresenterPassLogin(this, this);


    @Override
    protected int getContentViewId() {
        return R.layout.activity_pass_login;
    }

    @Override
    protected void init() {
        initView();
        initEvent();
    }

    private void initEvent() {
        mEtMobile.addTextChangedListener(new TextWatcher() {
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
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
                    mCleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mCleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(mContext, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtPassword.setSelection(s.length());
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
                    RxAnimationTool.zoomIn(mLogo, scale, dist);
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
                    RxAnimationTool.zoomOut(mLogo, scale);
                }
//                    mService.setVisibility(View.VISIBLE);
            }
        });

//        mBtnLogin.setOnClickListener(v -> RxKeyboardTool.hideSoftInput(ActivityPassLogin.this));
    }

    private void initView() {
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3
    }

    private boolean checkPhone() {
        if (!RxRegTool.isMobile(String.valueOf(mEtMobile.getText()))) {
            RxToast.error("请输入正确手机号");
            return false;
        }
        return true;
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.iv_clean_phone, R.id.clean_password, R.id.iv_show_pwd, R.id.btn_login2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clean_phone:
                mEtMobile.setText("");
                break;
            case R.id.clean_password:
                mEtPassword.setText("");
                break;
            case R.id.iv_show_pwd:
                if (mEtPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_visuable);
                } else {
                    mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_gone);
                }
                String pwd = mEtPassword.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    mEtPassword.setSelection(pwd.length());
                break;
            case R.id.btn_login2:
                RxKeyboardTool.hideSoftInput(ActivityPassLogin.this);
//                if (checkPhone())
                    persenter.login();
                break;
        }
    }

    @Override
    public String getPhone() {
        return mEtMobile.getText() + "";
    }

    @Override
    public String getPassWord() {
        return mEtPassword.getText() + "";
    }

    @Override
    public void showResult() {
        ActivityHome.start(mContext);
    }
}
