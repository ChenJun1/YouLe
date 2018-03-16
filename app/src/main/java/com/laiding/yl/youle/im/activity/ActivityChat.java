package com.laiding.yl.youle.im.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.runtimepermissions.PermissionsManager;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragmentActivity;
import com.laiding.yl.youle.im.fragment.ChatFragment;

/**
 * Created by JunChen on 2018/1/12.
 * Remarks  聊天
 */

public class ActivityChat extends MyBaseFragmentActivity {

    public static void start(Context context, String uid, String DescripionTitle,String DescripionContent) {
        Intent starter = new Intent(context, ActivityChat.class);
        starter.putExtra(EaseConstant.EXTRA_USER_ID, uid);
        starter.putExtra(EaseConstant.EXTRA_DESCRIPIONTITLE, DescripionTitle);
        starter.putExtra(EaseConstant.EXTRA_DESCRIPIONCONTENT, DescripionContent);
        context.startActivity(starter);
    }

    EaseChatFragment mFragment;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void init() {
        mFragment = new ChatFragment();
        mFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.chat_fragment, mFragment).commit();
    }

    @Override
    protected void initBundleData() {

    }

}
