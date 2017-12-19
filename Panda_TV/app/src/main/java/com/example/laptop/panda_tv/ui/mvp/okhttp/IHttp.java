package com.example.laptop.panda_tv.ui.mvp.okhttp;

import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;

/**
 * Created by Laptop on 2017/12/19.
 */
public interface IHttp {
    <T> void get(String url, CallBackListener<T> listener);

    <T> void post(String url, CallBackListener<T> listener);
}
