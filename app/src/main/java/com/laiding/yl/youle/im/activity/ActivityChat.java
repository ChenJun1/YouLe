package com.laiding.yl.youle.im.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.runtimepermissions.PermissionsManager;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.im.fragment.ChatFragment;

/**
 * Created by JunChen on 2018/1/12.
 * Remarks
 */

public class ActivityChat extends BaseActivity {

    public static void start(Context context, String uid) {
        Intent starter = new Intent(context, ActivityChat.class);
        starter.putExtra(EaseConstant.EXTRA_USER_ID, uid);
        context.startActivity(starter);
    }

    EaseChatFragment mFragment;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_chat);
        mFragment = new ChatFragment();
        mFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.chat_fragment, mFragment).commit();
    }


}
