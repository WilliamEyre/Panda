package com.example.laptop.panda_tv.ui.home.hudong.presenter;

import com.example.laptop.panda_tv.ui.home.hudong.bean.MyInteractionBean;
import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;
import com.example.laptop.panda_tv.ui.mvp.model.HomeModel;
import com.example.laptop.panda_tv.ui.mvp.model.IHomeModel;
import com.example.laptop.panda_tv.ui.mvp.presenter.IHomePresenter;
import com.example.laptop.panda_tv.ui.mvp.view.IHomeView;

/**
 * Created by Laptop on 2017/12/19.
 */
public class InteractionPresenter implements IHomePresenter{
    private IHomeModel iHomeModel;
    private IHomeView iHomeView;

    public InteractionPresenter(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        this.iHomeModel = new HomeModel();
    }

    @Override
    public void setBridge() {
        iHomeModel.getInteractionData(new CallBackListener<MyInteractionBean>() {
            @Override
            public void onError(String e) {

            }

            @Override
            public void onSuccess(MyInteractionBean interactiveBean) {
                iHomeView.updateInteractionData(interactiveBean);
            }
        });
    }
}
