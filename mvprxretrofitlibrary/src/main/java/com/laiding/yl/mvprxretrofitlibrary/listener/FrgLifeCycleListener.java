package com.laiding.yl.mvprxretrofitlibrary.listener;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * 生命周期监听
 *
 * @author JunChen
 */

public interface FrgLifeCycleListener {

    void onAttach(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroyView();

    void onDestroy();

    void onDetach();

}
