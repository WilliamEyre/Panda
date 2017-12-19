package com.example.laptop.panda_tv.ui.mvp.presenter;

import com.example.laptop.panda_tv.ui.home.bean.MyHomeBean;
import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;
import com.example.laptop.panda_tv.ui.mvp.model.HomeModel;
import com.example.laptop.panda_tv.ui.mvp.model.IHomeModel;
import com.example.laptop.panda_tv.ui.mvp.view.IHomeView;

/**
 * Created by Laptop on 2017/12/19.
 */
public class HomePresenter implements IHomePresenter{
    private IHomeModel iHomeModel;
    private IHomeView iHomeView;

    public HomePresenter(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        this.iHomeModel = new HomeModel();
    }

    @Override
    public void setBridge() {
        iHomeModel.getData(new CallBackListener<MyHomeBean>() {
            @Override
            public void onError(String e) {

            }

            @Override
            public void onSuccess(MyHomeBean myHomeBean) {
                iHomeView.updateUI(myHomeBean);
            }
        });

    }
}
