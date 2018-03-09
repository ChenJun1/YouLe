package com.laiding.yl.youle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.share.Defaultcontent;
import com.laiding.yl.youle.share.ShareUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.ShareBoardlistener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/3/9.
 * Remarks
 */

public class ShareActivity extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ShareActivity.class);
        context.startActivity(starter);
    }
    @BindView(R.id.button2)
    Button mButton2;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_share;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initBundleData() {

    }

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

    private ShareBoardlistener mShareBoardlistener = (snsPlatform, share_media) -> {
        switch (snsPlatform.mPlatform.name()) {
            case "QQ":
                ShareUtils.shareWeb(ShareActivity.this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.QQ);
                break;
            case "QZONE":
                ShareUtils.shareWeb(ShareActivity.this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.QZONE
                );
                break;
            case "SINA":
                ShareUtils.shareWeb(ShareActivity.this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.SINA
                );
                break;
            case "WEIXIN":
                ShareUtils.shareWeb(ShareActivity.this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN
                );
                break;
            case "WEIXIN_CIRCLE":
                ShareUtils.shareWeb(ShareActivity.this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN_CIRCLE
                );
                break;
        }
    };

    @OnClick(R.id.button2)
    public void onViewClicked() {
        new ShareAction(ShareActivity.this)
                .withText("hello").setShareboardclickCallback(mShareBoardlistener)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                })
                .open();
    }
}
