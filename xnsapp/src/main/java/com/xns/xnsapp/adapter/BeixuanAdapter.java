package com.xns.xnsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xns.xnsapp.R;
import com.xns.xnsapp.beans.Item;

import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class BeixuanAdapter extends MyBaseAdapter<Item> {

    public BeixuanAdapter(Context context, List<Item> list) {
        super(context, list);
    }

    @Override
    public View initItemView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_beixuan_item,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        Item item=list.get(position);
        mBitmapUtils.display(holder.ivGoods,item.getPoster());
        holder.tvGoodsName.setText(item.getName());
        holder.tvGoodsType.setText(item.getCat_name());
        return convertView;
    }

    private class ViewHolder{
        ImageView ivGoods;
        TextView tvGoodsName;
        TextView tvGoodsType;
        ImageView ivGo;

        public ViewHolder(View view){
            ivGoods= (ImageView) view.findViewById(R.id.iv_goods);
            tvGoodsName= (TextView) view.findViewById(R.id.tv_goodsname);
            tvGoodsType= (TextView) view.findViewById(R.id.tv_goodtype);
            ivGo= (ImageView) view.findViewById(R.id.iv_go);
        }
    }
}
