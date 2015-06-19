package com.xns.xnsapp.activity;

import android.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xns.xnsapp.R;
import com.xns.xnsapp.adapter.ListCommentAdapter;
import com.xns.xnsapp.adapter.TagAdapter;
import com.xns.xnsapp.beans.Comment_list;
import com.xns.xnsapp.beans.Comments;
import com.xns.xnsapp.beans.Lesson_list;
import com.xns.xnsapp.contants.UrlContants;
import com.xns.xnsapp.uis.RoundImageView;
import com.xns.xnsapp.utils.BaseApp;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kinnyo-imac-24 on 15-6-17.
 */
public class MainQuestionActivity extends BaseActivity{
    private HttpUtils mHttpUtils;
    private ActionBar mActionBar;
    private Lesson_list item;
    private Comment_list comment_list;

    private List<Comments> comments;
    private ListCommentAdapter commentAdapter;

    //ActionBar的对应的控件
    private ImageView ivBack;
    private ImageView ivLike;
    private ImageView ivChat;
    private ImageView ivShare;

    private BaseApp baseApp;
    private BitmapUtils mBitmapUtils;
    private TagAdapter adapter;

    private String user_token="",page="0",id,sort="0";
    private  String url;
    //activity拥有的控件
    @ViewInject(R.id.circle_usericon)
    private RoundImageView roundUserIcon;

    @ViewInject(R.id.tv_username)
    private TextView tvUserName;

    @ViewInject(R.id.tv_time)
    private TextView tvTime;

    @ViewInject(R.id.gv_tag)
    private GridView gvTag;

    @ViewInject(R.id.tv_main_title)
    private TextView tvTitle;

    @ViewInject(R.id.tv_main_content)
    private TextView tvContent;

    @ViewInject(R.id.tv_like_count)
    private TextView tvLikeCount;

    @ViewInject(R.id.tv_comment_count)
    private TextView tvCommentCount;

    @ViewInject(R.id.iv_add_response)
    private ImageView ivAddComment;

    @ViewInject(R.id.lv_comments)
    private ListView lvComments;

    @Override
    public void initView() {
        setContentView(R.layout.activity_question);
        ViewUtils.inject(this);
        initActionBarView();
        item= (Lesson_list) getIntent().getSerializableExtra("item");
        baseApp=(BaseApp)getApplication();
        mBitmapUtils=baseApp.mBitmapUtils;
        mHttpUtils=new HttpUtils();
        adapter=new TagAdapter(this,item.getArr_tag());
        //评论的数据源和适配器
        comments=new ArrayList<Comments>();
        commentAdapter=new ListCommentAdapter(this,comments);
        lvComments.setAdapter(commentAdapter);

        url= UrlContants.getQuestionComment();
        mBitmapUtils.display(roundUserIcon, item.getAuthor_avatar());
        tvUserName.setText(item.getAuthor_nickname());
        tvTime.setText(item.getDate());
        gvTag.setAdapter(adapter);

        id=item.getId();
        String title=item.getTitle();
        String content=item.getSummary();
        String likecount=item.getThumbsup_count();
        String commentcount=item.getComment_count();
        if(TextUtils.isEmpty(likecount)){
            tvLikeCount.setText("有0人点赞");
        }else {
            tvLikeCount.setText("有"+likecount+"人点赞");
        }

        if(TextUtils.isEmpty(commentcount)){
            tvCommentCount.setText("有0条回答");
        }else {
            tvCommentCount.setText("有"+commentcount+"条回答");
        }

        if(TextUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setText(title);
        }

        if(TextUtils.isEmpty(content)){
            tvContent.setVisibility(View.GONE);
        }else {
            tvContent.setText(content);
        }
    }

    @Override
    public void loadData() {
        String postJson="{"+"\"user_token\""+":"+"\""+user_token+"\""+","
                +"\"page\""+":"+"\""+page+"\""+","+"\"id\""+":"+"\""+id+"\""+","+"\"sort\""+":"+"\""+sort+"\""+"}";
        try {
            StringEntity se=new StringEntity(postJson);
            RequestParams params=new RequestParams();
            params.setBodyEntity(se);
            mHttpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String text = responseInfo.result;
                    comment_list = JSONObject.parseObject(text, Comment_list.class);
                    comments.clear();
                    comments.addAll(comment_list.getComments());
                    commentAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(MainQuestionActivity.this,"获取评论失败,请检查网络状况",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //初始化ActionBar的控件
    private void initActionBarView(){
        MyActionBarClickListener listener=new MyActionBarClickListener();
        mActionBar=getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setCustomView(R.layout.title_main_question);
        View titleView=mActionBar.getCustomView();
        ivBack=(ImageView)titleView.findViewById(R.id.iv_top_back);
        ivLike=(ImageView)titleView.findViewById(R.id.iv_top_like);
        ivChat=(ImageView)titleView.findViewById(R.id.iv_top_chat);
        ivShare=(ImageView)titleView.findViewById(R.id.iv_top_share);
        //点击监听
        ivBack.setOnClickListener(listener);
        ivLike.setOnClickListener(listener);
        ivChat.setOnClickListener(listener);
        ivShare.setOnClickListener(listener);

    }

    private class MyActionBarClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_top_back:
                    finish();
                    break;
                case R.id.iv_top_like:
                    Toast.makeText(MainQuestionActivity.this,"正在完善",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_top_chat:
                    Toast.makeText(MainQuestionActivity.this,"正在完善",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_top_share:
                    Toast.makeText(MainQuestionActivity.this,"正在完善",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
