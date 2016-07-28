package com.zhiyicx.zhibo.zbsmartlivesdkdemo;

import android.app.Application;

import com.zhiyicx.zhibosdk.ZBSmartLiveSDK;

/**
 * Created by jungle on 16/7/11.
 * com.zhiyicx.zhibo.zbsmartlivesdkdemo
 * ZBSmartLiveSDKDemo
 * email:335891510@qq.com
 */
public class BaseApplication extends Application {

    private static  BaseApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        /**
         * 初始化
         */
        ZBSmartLiveSDK.init(this);
    }
    public static BaseApplication getContext(){
        return mApplication;
    }
}
