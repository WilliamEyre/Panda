package com.example.laptop.panda_tv.ui.pandaculture.mvp.model;

import com.example.laptop.panda_tv.ui.home.contact.Contact;
import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;
import com.example.laptop.panda_tv.ui.mvp.okhttp.OkHttpUtils;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PandaCultureModel implements IPandaCultureModel {
    @Override
    public <T> void getPandaCultureData(CallBackListener<T> listener) {
        OkHttpUtils.getInstance().get(Contact.PANDACULTURE,listener);
    }
}
