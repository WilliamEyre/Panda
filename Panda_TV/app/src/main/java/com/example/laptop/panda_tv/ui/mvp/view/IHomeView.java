package com.example.laptop.panda_tv.ui.mvp.view;

import com.example.laptop.panda_tv.ui.home.bean.MyHomeBean;
import com.example.laptop.panda_tv.ui.home.hudong.bean.MyInteractionBean;

/**
 * Created by Laptop on 2017/12/19.
 */
public interface IHomeView {
    void updateUI(MyHomeBean myHomeBean);

    void updateInteractionData(MyInteractionBean myInteractionBean);
}
