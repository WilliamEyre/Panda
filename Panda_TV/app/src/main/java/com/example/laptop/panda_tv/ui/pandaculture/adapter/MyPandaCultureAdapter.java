package com.example.laptop.panda_tv.ui.pandaculture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.pandaculture.bean.MyPandaCultureBean;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyPandaCultureAdapter extends BaseAdapter{
    List<MyPandaCultureBean.ListBean> mLvPandaCultureList;
    private Context context;

    public MyPandaCultureAdapter(List<MyPandaCultureBean.ListBean> mLvPandaCultureList, Context context) {
        this.mLvPandaCultureList = mLvPandaCultureList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mLvPandaCultureList.size();
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pandaobserve, parent, false);
            viewHolder.mImghomeShadowChina = (ImageView) convertView.findViewById(R.id.img_homeShadowChina);
            viewHolder.mTvTitlehomeShadowChina = (TextView) convertView.findViewById(R.id.tv_title_homeShadowChina);
            viewHolder.mTvDayTimehomeShadowChina = (TextView) convertView.findViewById(R.id.tv_daytime_homeShadowChina);
            viewHolder.mRbPandaObserve = (RadioButton) convertView.findViewById(R.id.rb_PandaObserve);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTvTitlehomeShadowChina.setText(mLvPandaCultureList.get(position).getTitle());
        viewHolder.mTvDayTimehomeShadowChina.setText(mLvPandaCultureList.get(position).getBrief());
        viewHolder.mRbPandaObserve.setTag(mLvPandaCultureList.get(position).getVideoLength());
        Glide.with(context).load(mLvPandaCultureList.get(position).getImage()).into(viewHolder.mImghomeShadowChina);

        return convertView;
    }

    public class ViewHolder {

        public ImageView mImghomeShadowChina;
        public TextView mTvTitlehomeShadowChina;
        public TextView mTvDayTimehomeShadowChina;
        public RadioButton mRbPandaObserve;
    }
}
