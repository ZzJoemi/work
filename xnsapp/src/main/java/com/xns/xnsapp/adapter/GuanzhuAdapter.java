package com.xns.xnsapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xns.xnsapp.R;
import com.xns.xnsapp.beans.Friend;
import com.xns.xnsapp.uis.RoundImageView;

import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class GuanzhuAdapter extends MyBaseAdapter<Friend> {

    public GuanzhuAdapter(Context context, List<Friend> list) {
        super(context, list);
    }

    @Override
    public View initItemView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.list_guanzhu_item,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        Friend friend=list.get(position);
        mBitmapUtils.display(holder.roundUserIcon, friend.getAvatar());
        holder.tvUserName.setText(friend.getNickname());
        if(TextUtils.isEmpty(friend.getCert_desc())){
            holder.tvMayTime.setText("婚期：　"+friend.getWedding_date());
        }else {
            holder.tvMayTime.setText(friend.getCert_desc());
        }
        holder.tvCollect.setText("已备选了"+friend.getCollect_count()+"家商户");
        //0不是好友，1是好友
        if(friend.getIs_my_friend().equals("1")){
            holder.ivAddFriend.setImageResource(R.mipmap.gz_added_friend);
        }else {
            holder.ivAddFriend.setImageResource(R.mipmap.gz_add_friend);
        }
        return convertView;
    }

    //控件持有者
    class ViewHolder{
        RoundImageView roundUserIcon;
        TextView tvUserName;
        TextView tvMayTime;
        TextView tvCollect;
        ImageView ivAddFriend;

        public ViewHolder(View view){
            roundUserIcon= (RoundImageView) view.findViewById(R.id.circle_usericon);
            tvUserName= (TextView) view.findViewById(R.id.tv_username);
            tvMayTime= (TextView) view.findViewById(R.id.tv_maytime);
            tvCollect= (TextView) view.findViewById(R.id.tv_collect);
            ivAddFriend= (ImageView) view.findViewById(R.id.iv_friend);
        }
    }
}
