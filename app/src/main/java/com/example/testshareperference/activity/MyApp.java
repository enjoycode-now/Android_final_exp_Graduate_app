package com.example.testshareperference.activity;

import android.app.Application;

import com.xuexiang.xui.XUI;


//没什么用的，之前用来渲染主题的
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
    }
}
