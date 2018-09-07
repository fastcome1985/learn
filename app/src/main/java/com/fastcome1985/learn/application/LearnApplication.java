package com.fastcome1985.learn.application;

import android.app.Application;
import android.util.Log;

import com.fastcome1985.environmentlib.AppEnvironment;
import com.fastcome1985.learn.BuildConfig;

/**
 * Created by luojianxiang on 2018/9/7.
 */

public class LearnApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initEnvironment();


        Log.i("LearnApplication", "isDebugEnvironment==" + AppEnvironment.isDebugEnvironment());
        Log.i("LearnApplication", "isPreReleaseEnvironment==" + AppEnvironment.isPreReleaseEnvironment());
        Log.i("LearnApplication", "isReleaseEnvironment==" + AppEnvironment.isReleaseEnvironment());
    }


    /**
     * 初始化环境
     */
    private void initEnvironment() {
        if (BuildConfig.PRE) {
            AppEnvironment.initPreReleaseEnvironment();
        } else {
            AppEnvironment.initEnvironment(this);
        }
    }
}
