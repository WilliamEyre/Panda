package com.example.laptop.panda_tv.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.home.bean.MyHomeShadowBean;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyHomeShadowChainaAdapter extends BaseAdapter{
    private List<MyHomeShadowBean.ListBean> newListBean;
    private Context context;

    public MyHomeShadowChainaAdapter(List<MyHomeShadowBean.ListBean> newListBean, Context context) {
        this.newListBean = newListBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newListBean.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_shadow_china, parent,false);
            viewHolder.mImghomeShadowChina = (ImageView) convertView.findViewById(R.id.img_homeShadowChina);
            viewHolder.mTvTitlehomeShadowChina = (TextView) convertView.findViewById(R.id.tv_title_homeShadowChina);
            viewHolder.mTvDayTimehomeShadowChina = (TextView) convertView.findViewById(R.id.tv_daytime_homeShadowChina);
            viewHolder.mRbHomeShadowChina = (TextView) convertView.findViewById(R.id.rb_homeShadowChina);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTvTitlehomeShadowChina.setText(newListBean.get(position).getTitle());
        viewHolder.mTvDayTimehomeShadowChina.setText(newListBean.get(position).getDaytime());
        viewHolder.mRbHomeShadowChina.setText(newListBean.get(position).getVideoLength());
        Glide.with(context).load(newListBean.get(position).getImage()).into(viewHolder.mImghomeShadowChina);
        return convertView;
    }

    public class ViewHolder {
        public ImageView mImghomeShadowChina;
        public TextView mTvTitlehomeShadowChina;
        public TextView mTvDayTimehomeShadowChina;
        public TextView mRbHomeShadowChina;
    }
}
