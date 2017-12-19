package com.example.laptop.panda_tv.ui.home.hudong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.home.hudong.activity.InteractionDetailsActivity;
import com.example.laptop.panda_tv.ui.home.hudong.bean.MyInteractionBean;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyInteractionAdapter extends RecyclerView.Adapter<MyInteractionAdapter.ViewHolder>{
    private List<MyInteractionBean.InteractiveBean> interactive;
    private Context context;

    public MyInteractionAdapter(List<MyInteractionBean.InteractiveBean> interactive, Context context) {
        this.interactive = interactive;
        this.context = context;
    }

    @Override
    public MyInteractionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_interaction, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyInteractionAdapter.ViewHolder holder, final int position) {
        holder.tvInteraction.setText(interactive.get(position).getTitle());
        Glide.with(context).load(interactive.get(position).getImage()).into(holder.imgInteraction);
        holder.lineInteraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InteractionDetailsActivity.class);
                intent.putExtra("url",interactive.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return interactive.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgInteraction;
        private TextView tvInteraction;
        private LinearLayout lineInteraction;

        public ViewHolder(View itemView) {
            super(itemView);

            imgInteraction = (ImageView) itemView.findViewById(R.id.img_interaction);
            tvInteraction = (TextView) itemView.findViewById(R.id.tv_interaction);
            lineInteraction = (LinearLayout) itemView.findViewById(R.id.line_interaction);

        }
    }
}
