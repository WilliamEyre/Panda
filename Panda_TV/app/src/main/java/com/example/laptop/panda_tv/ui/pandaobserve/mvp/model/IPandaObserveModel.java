package com.example.laptop.panda_tv.ui.pandaobserve.mvp.model;

import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;

/**
 * Created by Laptop on 2017/12/19.
 */
public interface IPandaObserveModel {
    <T> void getPandaObserveData(CallBackListener<T> listener);
}
