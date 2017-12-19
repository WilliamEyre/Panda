package com.example.laptop.panda_tv.ui.pandaculture.mvp.model;

import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;

/**
 * Created by Laptop on 2017/12/19.
 */
public interface IPandaCultureModel {
    <T> void getPandaCultureData(CallBackListener<T> listener);
}
