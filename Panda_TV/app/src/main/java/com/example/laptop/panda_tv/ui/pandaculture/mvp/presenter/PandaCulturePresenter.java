package com.example.laptop.panda_tv.ui.pandaculture.mvp.presenter;

import com.example.laptop.panda_tv.ui.MyApplication;
import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;
import com.example.laptop.panda_tv.ui.mvp.presenter.IHomePresenter;
import com.example.laptop.panda_tv.ui.pandaculture.bean.MyPandaCultureBean;
import com.example.laptop.panda_tv.ui.pandaculture.mvp.model.IPandaCultureModel;
import com.example.laptop.panda_tv.ui.pandaculture.mvp.model.PandaCultureModel;
import com.example.laptop.panda_tv.ui.pandaculture.mvp.view.IPandaCultureView;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PandaCulturePresenter implements IHomePresenter{
    private IPandaCultureView iPandaCultureView;
    private IPandaCultureModel iPandaCultureModel;

    public PandaCulturePresenter(IPandaCultureView iPandaCultureView) {
        this.iPandaCultureView = iPandaCultureView;
        this.iPandaCultureModel = new PandaCultureModel();
    }

    @Override
    public void setBridge() {
        iPandaCultureModel.getPandaCultureData(new CallBackListener<MyPandaCultureBean>() {
            @Override
            public void onError(String e) {

            }

            @Override
            public void onSuccess(final MyPandaCultureBean myPandaCultureBean) {
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iPandaCultureView.updatePandaCultureData(myPandaCultureBean);
                    }
                });
            }
        });
    }
}
