package com.example.laptop.panda_tv.ui.mvp.model;

import com.example.laptop.panda_tv.ui.home.contact.Contact;
import com.example.laptop.panda_tv.ui.mvp.okhttp.OkHttpUtils;

/**
 * Created by Laptop on 2017/12/19.
 */
public class HomeModel implements IHomeModel {

    @Override
    public <T> void getData(CallBackListener<T> listener) {
        OkHttpUtils.getInstance().get(Contact.HOMEUUL,listener);
    }

    @Override
    public <T> void getInteractionData(CallBackListener<T> listener) {
        OkHttpUtils.getInstance().get(Contact.INTERACTION,listener);
    }
}
