package com.example.laptop.panda_tv.ui;

import android.app.Application;

import com.example.laptop.panda_tv.*;
import com.example.laptop.panda_tv.BaseActivity;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyApplication extends Application{

    public static BaseActivity context;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
