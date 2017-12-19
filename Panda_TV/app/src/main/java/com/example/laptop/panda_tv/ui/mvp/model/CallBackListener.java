package com.example.laptop.panda_tv.ui.mvp.model;

/**
 * Created by Laptop on 2017/12/19.
 */
public interface CallBackListener<T> {
    void onError(String e);

    void  onSuccess(T t);
}
