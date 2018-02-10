package com.laiding.yl.youle.Information.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.laiding.yl.youle.widget.CommentListTextView;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks 资讯详情
 */

public class ActivityInformationDetail extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityInformationDetail.class);
        context.startActivity(starter);
    }

    @BindView(R.id.commentlist)
    CommentListTextView mCommentlist;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_bar_right)
    GlideImageView mIvBarRight;
    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;

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
        test();
    }

    @Override
    protected void initBundleData() {

    }

    private void test () {
//        mTextView.setMovementMethod (ScrollingMovementMethod.getInstance ());


        mCommentlist.setMaxlines (6);
        mCommentlist.setMoreStr ("查看更多");
        mCommentlist.setNameColor (Color.parseColor ("#fe671e"));
        mCommentlist.setCommentColor (Color.parseColor ("#242424"));
        mCommentlist.setTalkStr ("回复");
        mCommentlist.setTalkColor (Color.parseColor ("#242424"));


        List<CommentListTextView.CommentInfo> mCommentInfos = new ArrayList<>();
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (1111).setComment ("今天天气真好啊！11").setNickname ("张三").setTonickname ("赵四"));
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (2222).setComment ("今天天气真好啊！22").setNickname ("赵四"));
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (3333).setComment ("今天天气真好啊！33今天天气真好啊今天天气真好啊今天天气真好啊今天天气真好啊").setNickname ("王五").setTonickname ("小三"));
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (4444).setComment ("今天天气真好啊今天天气真好啊！44今天天气真好啊今天天气真好啊").setNickname ("小三").setTonickname ("王五"));
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (5555).setComment ("今天天气真好啊！55").setNickname ("李大"));
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (6666).setComment ("今天天气真好啊！66").setNickname ("小三").setTonickname ("王五"));
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (7777).setComment ("今天天气真好啊！77").setNickname ("李大").setTonickname ("张三"));
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (8888).setComment ("今天天气真好啊！88").setNickname ("小三").setTonickname ("王五"));
        mCommentInfos.add (new CommentListTextView.CommentInfo ().setID (9999).setComment ("今天天气真好啊！99").setNickname ("李大").setTonickname ("张三"));
        mCommentlist.setData (mCommentInfos);
        mCommentlist.setonCommentListener (new CommentListTextView.onCommentListener () {



            @Override
            public void onNickNameClick (final int position, final CommentListTextView.CommentInfo mInfo) {
//                mTextView.append ("onNickNameClick  position = [" + position + "], mInfo = [" + mInfo + "]" + "\r\n");
            }

            @Override
            public void onToNickNameClick (final int position, final CommentListTextView.CommentInfo mInfo) {
//                mTextView.append ("onToNickNameClick  position = [" + position + "], mInfo = [" + mInfo + "]" + "\r\n");
            }

            @Override
            public void onCommentItemClick (final int position, final CommentListTextView.CommentInfo mInfo) {
//                mTextView.append ("onCommentItemClick  position = [" + position + "], mInfo = [" + mInfo + "]" + "\r\n");
            }

            @Override
            public void onOtherClick () {
//                mTextView.append ("onOtherClick" + "\r\n");
            }
        });
    }

    @OnClick(R.id.ll_im_bar_right)
    public void onViewClicked() {
        ActivityChat.start(mContext, "8899");
    }

}
