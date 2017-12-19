package com.example.laptop.panda_tv.ui.mvp.model;

/**
 * Created by Laptop on 2017/12/19.
 */
public interface IHomeModel {
    <T> void getData(CallBackListener<T> listener);

    <T> void getInteractionData(CallBackListener<T> listener);
}
