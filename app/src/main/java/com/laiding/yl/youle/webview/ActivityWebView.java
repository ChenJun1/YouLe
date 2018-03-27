package com.laiding.yl.youle.webview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.just.agentweb.AbsAgentWebUIController;
import com.just.agentweb.MiddlewareWebChromeBase;
import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.BaseAgentWebActivity;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.laiding.yl.youle.login.activity.ActivityPhoneLogin;
import com.laiding.yl.youle.share.Defaultcontent;
import com.laiding.yl.youle.share.ShareUtils;
import com.sunfusheng.glideimageview.GlideImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/3/13.
 * Remarks
 */

public class ActivityWebView extends BaseAgentWebActivity {
    private static final String TAG = "ActivityWebView";
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.fenxiang_gliv)
    GlideImageView mFenxiangGliv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    LinearLayout mContainer;
    static final String URL = "URL";
    static final String TITLE = "TITLE";
    @BindView(R.id.fenxiang_ll)
    LinearLayout mFenxiangLl;
    private String url;
    private String mTitle;


    public static void start(Context context, String Url, String Title) {
        Intent starter = new Intent(context, ActivityWebView.class);
        starter.putExtra(URL, Url);
        starter.putExtra(TITLE, Title);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initBar();
    }

    private void initBar() {
        mTitle = getIntent().getStringExtra(TITLE) == null ? "" : getIntent().getStringExtra(TITLE);
        url = getIntent().getStringExtra(URL) == null ? "" : getIntent().getStringExtra(URL);
        mToolbarTitle.setText(mTitle);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        this.setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(v -> ActivityWebView.this.finish());

    }


    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return (ViewGroup) this.findViewById(R.id.container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void setTitle(WebView view, String title) {
        mToolbarTitle.setText(title);
    }

    @Override
    protected int getIndicatorColor() {
        return Color.parseColor("#ff0000");
    }

    @Override
    protected int getIndicatorHeight() {
        return 3;
    }

    @Nullable
    @Override
    protected String getUrl() {
        return getIntent().getStringExtra(URL) == null ? "" : getIntent().getStringExtra(URL);
    }


    private void initP() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    private ShareBoardlistener mShareBoardlistener = (snsPlatform, share_media) -> {
        switch (snsPlatform.mPlatform.name()) {
            case "QQ":
                initP();
                ShareUtils.shareWeb(this, url, mTitle
                        , mTitle, "", R.mipmap.im_fenxiang, SHARE_MEDIA.QQ);
                break;
            case "QZONE":
                initP();
                ShareUtils.shareWeb(this, url, mTitle
                        , mTitle, "", R.mipmap.im_fenxiang, SHARE_MEDIA.QZONE
                );
                break;
            case "SINA":
                initP();
                ShareUtils.shareWeb(this, url, mTitle
                        , mTitle, Defaultcontent.imageurl, R.mipmap.im_fenxiang, SHARE_MEDIA.SINA
                );
                break;
            case "WEIXIN":
                initP();
                ShareUtils.shareWeb(this, url, mTitle
                        , Defaultcontent.text, url, R.mipmap.im_fenxiang, SHARE_MEDIA.WEIXIN
                );
                break;
            case "WEIXIN_CIRCLE":
                initP();
                ShareUtils.shareWeb(this, url, mTitle
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.im_fenxiang, SHARE_MEDIA.WEIXIN_CIRCLE
                );
                break;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @OnClick(R.id.fenxiang_ll)
    public void onViewClicked() {
        ShareStart();
    }

    private void ShareStart() {
//          .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
        new ShareAction(ActivityWebView.this)
                .withText("有了me").setShareboardclickCallback(mShareBoardlistener)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        LogUtils.i("onStart==Shar");
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        LogUtils.i("onResult==Shar");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        LogUtils.i("onError==Shar");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        LogUtils.i("onCancel==Shar");
                    }
                })
                .open();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }

    @Nullable
    @Override
    protected WebViewClient getWebViewClient() {
        return mWebViewClient;
    }

    protected WebViewClient mWebViewClient = new WebViewClient() {


        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @SuppressLint("NewApi")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl() + "");
        }
        // 拦截合法代孕 咨询页面
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            LogUtils.d("mUrl:" + url + " onPageStarted  target:" + getUrl());
            if (url != null && "http://m.51laiding.xyz/hfdy/youleDidPushPage=x".equals(url)) {
                if (!EMClient.getInstance().isConnected()) {
                    isTokenExpired("客服未连接,请重新登录");
                } else {
                    ActivityChat.start(ActivityWebView.this, "19", "", "");
                }
                return true;
            }
            return false;
        }
    };


    public void isTokenExpired(String erreMsg) {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(ActivityWebView.this);//提示弹窗
        rxDialogSureCancel.setContent(erreMsg);
        rxDialogSureCancel.getTitleView().setBackgroundResource(R.mipmap.home_log);
        rxDialogSureCancel.getSureView().setOnClickListener(v -> {

            //环信退出
            EMClient.getInstance().logout(true, new EMCallBack() {

                @Override
                public void onSuccess() {
                    // TODO Auto-generated method stub
                    LogUtils.e("推出成功");
                }

                @Override
                public void onProgress(int progress, String status) {
                }

                @Override
                public void onError(int code, String message) {
                    // TODO Auto-generated method stub
                    LogUtils.e("推出失败");
                }

            });
            ActivityStackManager.getManager().finishAllActivity();
            this.finish();
            ActivityPhoneLogin.start(ActivityWebView.this);
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(v -> rxDialogSureCancel.cancel());
        rxDialogSureCancel.show();
    }


}
