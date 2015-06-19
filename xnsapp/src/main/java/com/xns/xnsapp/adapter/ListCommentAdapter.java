package com.xns.xnsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xns.xnsapp.R;
import com.xns.xnsapp.beans.Arr_comment;
import com.xns.xnsapp.beans.Comments;
import com.xns.xnsapp.uis.RoundImageView;


import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-17.
 */
public class ListCommentAdapter extends MyBaseAdapter<Comments> {

    public ListCommentAdapter(Context context, List<Comments> list) {
        super(context, list);
    }

    @Override
    public View initItemView(int position, View convertView, ViewGroup arg2) {
        CommentViewHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_comment_item,null);
            holder=new CommentViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder=(CommentViewHolder)convertView.getTag();
        }
        Comments comments=list.get(position);
        mBitmapUtils.display(holder.roundUserIcon,comments.getAvatar());
        holder.tvUserName.setText(comments.getNickname());
        holder.tvTime.setText(comments.getCreate_time());
        holder.tvComment.setText(comments.getSummary());
        holder.tvLikeCount.setText(comments.getThumbsup_count());
        //holder.tvCommentCount.setText();
        return convertView;
    }

    //控件持有者
    class CommentViewHolder{
        @ViewInject(R.id.circle_usericon)
        RoundImageView roundUserIcon;

        @ViewInject(R.id.tv_username)
        TextView tvUserName;

        @ViewInject(R.id.tv_time)
        TextView tvTime;

        @ViewInject(R.id.tv_comment)
        TextView tvComment;

        @ViewInject(R.id.tv_like_count)
        TextView tvLikeCount;

        @ViewInject(R.id.tv_comment_count)
        TextView tvCommentCount;

        public CommentViewHolder(View view){
            ViewUtils.inject(this,view);
        }
    }
}
