package com.example.laptop.panda_tv.ui.pandaobserve.mvp.model;

import com.example.laptop.panda_tv.ui.home.contact.Contact;
import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;
import com.example.laptop.panda_tv.ui.mvp.okhttp.OkHttpUtils;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PandaObserveModel implements IPandaObserveModel {
    @Override
    public <T> void getPandaObserveData(CallBackListener<T> listener) {
        OkHttpUtils.getInstance().get(Contact.PANDAOBERVE,listener);
    }
}
