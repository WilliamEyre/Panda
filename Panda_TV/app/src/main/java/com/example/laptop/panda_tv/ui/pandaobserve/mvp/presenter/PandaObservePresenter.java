package com.example.laptop.panda_tv.ui.pandaobserve.mvp.presenter;

import com.example.laptop.panda_tv.ui.MyApplication;
import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;
import com.example.laptop.panda_tv.ui.mvp.presenter.IHomePresenter;
import com.example.laptop.panda_tv.ui.pandaobserve.bean.MyPandaObserveBean;
import com.example.laptop.panda_tv.ui.pandaobserve.mvp.model.IPandaObserveModel;
import com.example.laptop.panda_tv.ui.pandaobserve.mvp.model.PandaObserveModel;
import com.example.laptop.panda_tv.ui.pandaobserve.mvp.view.IPandaOberveView;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PandaObservePresenter implements IHomePresenter{
    private IPandaOberveView iPandaOberveView;
    private IPandaObserveModel iPandaObserveModel;

    public PandaObservePresenter(IPandaOberveView iPandaOberveView) {
        this.iPandaOberveView = iPandaOberveView;
        this.iPandaObserveModel = new PandaObserveModel();
    }

    @Override
    public void setBridge() {
        iPandaObserveModel.getPandaObserveData(new CallBackListener<MyPandaObserveBean>() {
            @Override
            public void onError(String e) {

            }

            @Override
            public void onSuccess(final MyPandaObserveBean dataBean) {
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iPandaOberveView.updatePandaOberveData(dataBean);
                    }
                });
            }
        });
    }
}
