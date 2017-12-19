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
import com.example.laptop.panda_tv.ui.home.bean.MyHomeBean;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MySplendidTitleAdapter extends BaseAdapter{
    private List<MyHomeBean.DataBean.AreaBean> areaBeanList;
    private Context context;

    public MySplendidTitleAdapter(List<MyHomeBean.DataBean.AreaBean> areaBeanList, Context context) {
        this.areaBeanList = areaBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return areaBeanList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_splendid_title, null);
            viewHolder.mImgSplendidTitle = (ImageView) convertView.findViewById(R.id.img_splendidTitle);
            viewHolder.mTvSplendidTitle = (TextView) convertView.findViewById(R.id.tv_splendidTitle);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTvSplendidTitle.setText(areaBeanList.get(position).getTitle());
        Glide.with(context).load(areaBeanList.get(position).getImage()).into(viewHolder.mImgSplendidTitle);
        return convertView;
    }

    public class ViewHolder {
        public ImageView mImgSplendidTitle;
        public TextView mTvSplendidTitle;
    }
}
