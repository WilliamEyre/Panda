package com.example.laptop.panda_tv.ui.pandaobserve.adapter;

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
import com.example.laptop.panda_tv.ui.pandaobserve.bean.MyPandaObserveRecyclerBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyPandaObserveAdapter extends BaseAdapter{
    List<MyPandaObserveRecyclerBean.ListBean> mPandaObserveList;
    private Context context;

    public MyPandaObserveAdapter(List<MyPandaObserveRecyclerBean.ListBean> mPandaObserveList, Context context) {
        this.mPandaObserveList = mPandaObserveList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mPandaObserveList.size();
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
        String s = transferLongToDate("yyyy-MM-dd", mPandaObserveList.get(position).getFocus_date());
        viewHolder.mTvTitlehomeShadowChina.setText(mPandaObserveList.get(position).getTitle());
        viewHolder.mTvDayTimehomeShadowChina.setText(s);
        viewHolder.mRbPandaObserve.setText(mPandaObserveList.get(position).getVideolength());
        Glide.with(context).load(mPandaObserveList.get(position).getPicurl()).into(viewHolder.mImghomeShadowChina);

        return convertView;
    }

    public class ViewHolder {

        public ImageView mImghomeShadowChina;
        public TextView mTvTitlehomeShadowChina;
        public TextView mTvDayTimehomeShadowChina;
        public RadioButton mRbPandaObserve;
    }

    /**
     * 2      * 把long 转换成 日期 再转换成String类型
     * 3
     */
    public String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }
}
