package com.xns.xnsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.xns.xnsapp.R;
import com.xns.xnsapp.beans.HuodongItem;

import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class HuodongAdapter extends MyBaseAdapter<HuodongItem> {
    public HuodongAdapter(Context context, List<HuodongItem> list) {
        super(context, list);
    }

    @Override
    public View initItemView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_huodong_item,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        HuodongItem item=list.get(position);
        mBitmapUtils.display(holder.ivHuodong,item.getPoster());
        holder.tvHuodongName.setText(item.getTitle());
        holder.tvHuodongTime.setText("时间：" + item.getTime());
        holder.tvHuodongType.setText("类型："+item.getType());
        return convertView;
    }

    private class ViewHolder{
        ImageView ivHuodong;
        TextView tvHuodongName;
        TextView tvHuodongTime;
        TextView tvHuodongType;

        public ViewHolder(View view){
            ivHuodong= (ImageView) view.findViewById(R.id.iv_huodong);
            tvHuodongName= (TextView) view.findViewById(R.id.tv_huodongname);
            tvHuodongTime= (TextView) view.findViewById(R.id.tv_huodongtime);
            tvHuodongType= (TextView) view.findViewById(R.id.tv_huodongtype);
        }
    }
}
