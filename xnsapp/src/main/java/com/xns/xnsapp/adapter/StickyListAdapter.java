package com.xns.xnsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;
import com.lidroid.xutils.BitmapUtils;


import com.xns.xnsapp.R;
import com.xns.xnsapp.activity.HomePageActivity;
import com.xns.xnsapp.activity.MainLessonActivity;
import com.xns.xnsapp.activity.MainQuestionActivity;
import com.xns.xnsapp.beans.Arr_comment;
import com.xns.xnsapp.beans.Arr_tag;
import com.xns.xnsapp.beans.Lesson_list;
import com.xns.xnsapp.uis.RoundImageView;
import com.xns.xnsapp.utils.BaseApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-16.
 */
public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private Context context;
    private List<Lesson_list> lesson_lists;
    private BitmapUtils mBitmapUtils;

    public StickyListAdapter(Context context, List<Lesson_list> lesson_list) {
        this.context = context;
        this.lesson_lists = lesson_list;
        BaseApp baseApp=(BaseApp)context.getApplicationContext();
        mBitmapUtils=baseApp.mBitmapUtils;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        final HeadViewHolder headHolder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.item_list_main_header,null);
            headHolder=new HeadViewHolder(convertView);
            convertView.setTag(headHolder);
        }else {
            headHolder=(HeadViewHolder)convertView.getTag();
        }
        Lesson_list item=lesson_lists.get(position);
        headHolder.tvUserName.setText(item.getAuthor_nickname());
        //头像
        //mBitmapUtils.display(headHolder.ivUserIcon,item.getAuthor_avatar());
        //mBitmapUtils.display(headHolder.circleUserIcon,item.getAuthor_avatar());
        final String user_id=item.getAuthor_id();
        mBitmapUtils.display(headHolder.roundUserIcon, item.getAuthor_avatar());
        headHolder.tvUserDate.setText(item.getAuthor_date());
        headHolder.tvDate.setText(item.getDate());
        final Intent intent=new Intent(context, HomePageActivity.class);
        intent.putExtra("user_id",user_id);
        headHolder.roundUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });
        headHolder.tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return lesson_lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lesson_lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BodyViewHolder bodyHolder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.item_list_main_content,null);
            bodyHolder=new BodyViewHolder(convertView);
            convertView.setTag(bodyHolder);
        }else {
            bodyHolder=(BodyViewHolder)convertView.getTag();
        }
        final Lesson_list item=lesson_lists.get(position);
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
        final String type=item.getType();

        //内容标题
        if (TextUtils.isEmpty(title)) {
            bodyHolder.tvTitle.setVisibility(View.GONE);
        } else {
            bodyHolder.tvTitle.setText(title);
            bodyHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
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
            bodyHolder.ivPoster.setVisibility(View.GONE);
        } else {
            mBitmapUtils.display(bodyHolder.ivPoster, img);
        }

        //内容正文
        if (TextUtils.isEmpty(content)) {
            bodyHolder.tvContent.setVisibility(View.GONE);
        } else {
            bodyHolder.tvContent.setText(content);

            bodyHolder.tvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(type.equals("question")){
                        Intent intentQuestion=new Intent(context, MainQuestionActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("item",item);
                        intentQuestion.putExtras(bundle);
                        context.startActivity(intentQuestion);
                    }else if(type.equals("gathering")){
                        Toast.makeText(context,"该类型的界面还在开发中。。。",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"该类型的界面还在开发中。。。",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        //评论条数
        if (TextUtils.isEmpty(commentcount)) {
            bodyHolder.linearAllComment.setVisibility(View.GONE);
        } else {
            bodyHolder.tvCommentCount.setText(commentcount);
        }

        if (arr_comments.size() == 0) {
            bodyHolder.linearAllComment.setVisibility(View.GONE);
        } else {
            bodyHolder.linearAllComment.setVisibility(View.VISIBLE);
            for (int i = 0; i < arr_comments.size(); i++) {
                Arr_comment arr_comment = arr_comments.get(i);
                TextView tvName = bodyHolder.tvCommentNames.get(i);
                TextView tvBody = bodyHolder.tvCommentBodies.get(i);
                tvName.setText(arr_comment.getNickname() + ": ");
                tvBody.setText(arr_comment.getSummary());
            }
        }
        if (TextUtils.isEmpty(clicks)) {
            bodyHolder.tvClicks.setVisibility(View.GONE);
        } else {
            bodyHolder.tvClicks.setText(clicks+"人看过");
        }
        //bodyHolder.gvTag.setVisibility(View.GONE);
        			if(arr_tags.size()==0){
        				bodyHolder.gvTag.setVisibility(View.GONE);
        			}else{
                        bodyHolder.gvTag.setVisibility(View.VISIBLE);
        				bodyHolder.gvTag.setAdapter(new TagAdapter(context,arr_tags));
        			}
        //底部部分
        if (TextUtils.isEmpty(likeCount)) {
            bodyHolder.tvBottomLikeCount.setText("0");
        } else {
            bodyHolder.tvBottomLikeCount.setText(likeCount);
        }

        if (TextUtils.isEmpty(commentcount)) {
            bodyHolder.tvBottomCommentCount.setText("0");
        } else {
            bodyHolder.tvBottomCommentCount.setText(commentcount);
        }

        if (TextUtils.isEmpty(shareCount)) {
            bodyHolder.tvBottomShareCount.setText("0");
        } else {
            bodyHolder.tvBottomShareCount.setText(shareCount);
        }
        return convertView;
    }

    class HeadViewHolder {

        //ImageView ivUserIcon;
        //CircleImageView circleUserIcon;
        RoundImageView roundUserIcon;
        TextView tvUserName;
        TextView tvUserDate;
        TextView tvDate;
        //RelativeLayout relativeLayout;

        public HeadViewHolder(View view) {
            //relativeLayout=(RelativeLayout)view.findViewById(R.id.relative_header);
            //ivUserIcon = (ImageView) view.findViewById(R.id.circle_usericon);
            //circleUserIcon=(CircleImageView)view.findViewById(R.id.circle_usericon);
            roundUserIcon=(RoundImageView)view.findViewById(R.id.circle_usericon);
            tvUserName = (TextView) view.findViewById(R.id.tv_username);
            tvUserDate=(TextView)view.findViewById(R.id.tv_marrytime);
            tvDate= (TextView) view.findViewById(R.id.tv_time);

        }
    }

    class BodyViewHolder {
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
        List<TextView> tvCommentBodies=new ArrayList<TextView>();
        public BodyViewHolder(View view) {
            ivPoster = (ImageView) view.findViewById(R.id.iv_main_content);
            gvTag=(GridView)view.findViewById(R.id.gv_tag);
            tvTitle = (TextView) view.findViewById(R.id.tv_main_title);
            tvContent=(TextView)view.findViewById(R.id.tv_main_content);
            tvCommentCount=(TextView)view.findViewById(R.id.tv_comment_count);
            tvCommentName1=(TextView)view.findViewById(R.id.item_user_commentname1);
            tvCommentBody1=(TextView)view.findViewById(R.id.item_user_commentbody1);
            tvCommentName2=(TextView)view.findViewById(R.id.item_user_commentname2);
            tvCommentBody2=(TextView)view.findViewById(R.id.item_user_commentbody2);
            tvCommentName3=(TextView)view.findViewById(R.id.item_user_commentname3);
            tvCommentBody3=(TextView)view.findViewById(R.id.item_user_commentbody3);
            tvBottomLikeCount=(TextView)view.findViewById(R.id.tv_comment_like);
            tvBottomCommentCount=(TextView)view.findViewById(R.id.tv_comment_pl);
            tvBottomShareCount=(TextView)view.findViewById(R.id.tv_comment_share);
            tvClicks=(TextView)view.findViewById(R.id.tv_comment_look);
            linearAllComment=(LinearLayout)view.findViewById(R.id.linear_all_comment);
            tvCommentNames.add(tvCommentName1);
            tvCommentNames.add(tvCommentName2);
            tvCommentNames.add(tvCommentName3);
            tvCommentBodies.add(tvCommentBody1);
            tvCommentBodies.add(tvCommentBody2);
            tvCommentBodies.add(tvCommentBody3);

        }
    }
}
