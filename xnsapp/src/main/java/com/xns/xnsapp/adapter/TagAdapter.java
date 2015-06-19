package com.xns.xnsapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xns.xnsapp.R;
import com.xns.xnsapp.beans.Arr_tag;
import com.xns.xnsapp.uis.RoundImageView;

import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-15.
 */
public class TagAdapter extends BaseAdapter{
    private Context context;
    private List<Arr_tag> arr_tags;

    public TagAdapter(Context context, List<Arr_tag> arr_tags) {
        this.context = context;
        this.arr_tags = arr_tags;
    }

    @Override
    public int getCount() {
        return arr_tags.size();
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
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.tag_gridview_item,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        Arr_tag arr_tag=arr_tags.get(position);
        int color=Color.parseColor(arr_tag.getColor());
        //使用自定义shape，加载颜色
        GradientDrawable myGrad = (GradientDrawable)holder.ivTagColor.getBackground();
        myGrad.setColor(color);
        holder.tvTagContent.setText(arr_tag.getName());
        holder.tvTagContent.setTextColor(color);

        return convertView;
    }

    private class ViewHolder{
        ImageView ivTagColor;
        TextView tvTagContent;

        public ViewHolder(View view) {
            ivTagColor=(ImageView)view.findViewById(R.id.iv_tag_color);
            tvTagContent=(TextView)view.findViewById(R.id.tv_tag_content);
        }
    }
}
