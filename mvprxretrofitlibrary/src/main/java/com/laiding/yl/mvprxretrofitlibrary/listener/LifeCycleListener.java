package com.laiding.yl.mvprxretrofitlibrary.listener;

import android.os.Bundle;

/**
 * 生命周期监听
 *
 * @author JunChen
 * 
 */

public interface LifeCycleListener {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}
