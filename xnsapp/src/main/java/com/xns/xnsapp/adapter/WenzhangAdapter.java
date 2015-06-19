package com.xns.xnsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.xns.xnsapp.R;
import com.xns.xnsapp.activity.HomePageActivity;
import com.xns.xnsapp.activity.MainQuestionActivity;
import com.xns.xnsapp.beans.Arr_comment;
import com.xns.xnsapp.beans.Arr_tag;
import com.xns.xnsapp.beans.Lesson_list;
import com.xns.xnsapp.uis.RoundImageView;
import com.xns.xnsapp.utils.BaseApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-19.
 */
public class WenzhangAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Lesson_list> list;
    private BitmapUtils mBitmapUtils;

    public WenzhangAdapter(Context context, ArrayList<Lesson_list> list) {
        this.context = context;
        this.list = list;
        BaseApp baseApp = (BaseApp) context.getApplicationContext();
        mBitmapUtils = baseApp.mBitmapUtils;
    }

    @Override
    public int getCount() {
        return list.size();
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
        PageViewHolder holder = null;
        if (convertView != null && convertView instanceof LinearLayout) {
            holder = (PageViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_wenzhang_item, null);
            holder = new PageViewHolder(convertView);
            convertView.setTag(holder);
        }
        final Lesson_list item = list.get(position);
            /*
            头部部分
             */

        holder.tvUserName.setText(item.getAuthor_nickname());
        //头像
        final String user_id = item.getAuthor_id();
        mBitmapUtils.display(holder.roundUserIcon, item.getAuthor_avatar());
        holder.tvMarryDate.setText(item.getAuthor_date());
        holder.tvPostTime.setText(item.getDate());
        final Intent intent = new Intent(context, HomePageActivity.class);
        intent.putExtra("user_id", user_id);
        holder.roundUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });
        holder.tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });

            /*
            正文评论内容
             */
        String title = item.getTitle();
        String img = item.getPoster();
        final String content = item.getSummary();
        String commentcount = item.getComment_count();
        String clicks = item.getClicks();
        String likeCount = item.getThumbsup_count();
        String shareCount = item.getShare_count();
        // 评论内容
        List<Arr_comment> arr_comments = item.getArr_comment();
        //标签
        List<Arr_tag> arr_tags = item.getArr_tag();
        //点击内容详情进行页面跳转
        final String type = item.getType();

        //内容标题
        if (TextUtils.isEmpty(title)) {
            holder.tvTitle.setVisibility(View.GONE);
        } else {
            holder.tvTitle.setText(title);
            holder.tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type.equals("question")) {
                        Intent intentQuestion = new Intent(context, MainQuestionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("item", item);
                        intentQuestion.putExtras(bundle);
                        context.startActivity(intentQuestion);
                    } else if (type.equals("gathering")) {
                        Toast.makeText(context, "该类型的界面还在开发中。。。", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "该类型的界面还在开发中。。。", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (TextUtils.isEmpty(img)) {
            holder.ivPoster.setVisibility(View.GONE);
        } else {
            mBitmapUtils.display(holder.ivPoster, img);
        }

        //内容正文
        if (TextUtils.isEmpty(content)) {
            holder.tvContent.setVisibility(View.GONE);
        } else {
            holder.tvContent.setText(content);

            holder.tvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type.equals("question")) {
                        Intent intentQuestion = new Intent(context, MainQuestionActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("item", item);
                        intentQuestion.putExtras(bundle);
                        context.startActivity(intentQuestion);
                    } else if (type.equals("gathering")) {
                        Toast.makeText(context, "该类型的界面还在开发中。。。", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "该类型的界面还在开发中。。。", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        //评论条数
        if (TextUtils.isEmpty(commentcount)) {
            holder.linearAllComment.setVisibility(View.GONE);
        } else {
            holder.tvCommentCount.setText(commentcount);
        }

        if (arr_comments.size() == 0) {
            holder.linearAllComment.setVisibility(View.GONE);
        } else {
            holder.linearAllComment.setVisibility(View.VISIBLE);
            for (int i = 0; i < arr_comments.size(); i++) {
                Arr_comment arr_comment = arr_comments.get(i);
                TextView tvName = holder.tvCommentNames.get(i);
                TextView tvBody = holder.tvCommentBodies.get(i);
                tvName.setText(arr_comment.getNickname() + ": ");
                tvBody.setText(arr_comment.getSummary());
            }
        }
        if (TextUtils.isEmpty(clicks)) {
            holder.tvClicks.setVisibility(View.GONE);
        } else {
            holder.tvClicks.setText(clicks + "人看过");
        }
        //bodyHolder.gvTag.setVisibility(View.GONE);
        if (arr_tags.size() == 0) {
            holder.gvTag.setVisibility(View.GONE);
        } else {
            holder.gvTag.setVisibility(View.VISIBLE);
            holder.gvTag.setAdapter(new TagAdapter(context, arr_tags));
        }
        //底部部分
        if (TextUtils.isEmpty(likeCount)) {
            holder.tvBottomLikeCount.setText("0");
        } else {
            holder.tvBottomLikeCount.setText(likeCount);
        }

        if (TextUtils.isEmpty(commentcount)) {
            holder.tvBottomCommentCount.setText("0");
        } else {
            holder.tvBottomCommentCount.setText(commentcount);
        }

        if (TextUtils.isEmpty(shareCount)) {
            holder.tvBottomShareCount.setText("0");
        } else {
            holder.tvBottomShareCount.setText(shareCount);
        }


        return convertView;
    }

    /*
    内容的控件持有者
     */
    class PageViewHolder {
        RoundImageView roundUserIcon;
        TextView tvUserName;
        TextView tvMarryDate;
        TextView tvPostTime;
        ImageView ivPoster;
        GridView gvTag;
        TextView tvTitle;
        TextView tvContent;
        TextView tvCommentCount;
        TextView tvCommentName1;
        TextView tvCommentBody1;
        TextView tvCommentName2;
        TextView tvCommentBody2;
        TextView tvCommentName3;
        TextView tvCommentBody3;
        TextView tvBottomLikeCount;
        TextView tvBottomCommentCount;
        TextView tvBottomShareCount;
        TextView tvClicks;
        LinearLayout linearAllComment;
        List<TextView> tvCommentNames = new ArrayList<TextView>();
        List<TextView> tvCommentBodies = new ArrayList<TextView>();

        public PageViewHolder(View view) {
            roundUserIcon = (RoundImageView) view.findViewById(R.id.circle_usericon);
            tvUserName = (TextView) view.findViewById(R.id.tv_username);
            tvMarryDate = (TextView) view.findViewById(R.id.tv_marrytime);
            tvPostTime = (TextView) view.findViewById(R.id.tv_time);
            ivPoster = (ImageView) view.findViewById(R.id.iv_main_content);
            gvTag = (GridView) view.findViewById(R.id.gv_tag);
            tvTitle = (TextView) view.findViewById(R.id.tv_main_title);
            tvContent = (TextView) view.findViewById(R.id.tv_main_content);
            tvCommentCount = (TextView) view.findViewById(R.id.tv_comment_count);
            tvCommentName1 = (TextView) view.findViewById(R.id.item_user_commentname1);
            tvCommentBody1 = (TextView) view.findViewById(R.id.item_user_commentbody1);
            tvCommentName2 = (TextView) view.findViewById(R.id.item_user_commentname2);
            tvCommentBody2 = (TextView) view.findViewById(R.id.item_user_commentbody2);
            tvCommentName3 = (TextView) view.findViewById(R.id.item_user_commentname3);
            tvCommentBody3 = (TextView) view.findViewById(R.id.item_user_commentbody3);
            tvBottomLikeCount = (TextView) view.findViewById(R.id.tv_comment_like);
            tvBottomCommentCount = (TextView) view.findViewById(R.id.tv_comment_pl);
            tvBottomShareCount = (TextView) view.findViewById(R.id.tv_comment_share);
            tvClicks = (TextView) view.findViewById(R.id.tv_comment_look);
            linearAllComment = (LinearLayout) view.findViewById(R.id.linear_all_comment);
            tvCommentNames.add(tvCommentName1);
            tvCommentNames.add(tvCommentName2);
            tvCommentNames.add(tvCommentName3);
            tvCommentBodies.add(tvCommentBody1);
            tvCommentBodies.add(tvCommentBody2);
            tvCommentBodies.add(tvCommentBody3);

        }
    }
}
