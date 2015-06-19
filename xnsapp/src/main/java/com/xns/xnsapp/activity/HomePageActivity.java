package com.xns.xnsapp.activity;

import android.app.ActionBar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

import com.xns.xnsapp.R;
import com.xns.xnsapp.adapter.BeixuanAdapter;
import com.xns.xnsapp.adapter.GuanzhuAdapter;
import com.xns.xnsapp.adapter.HuodongAdapter;
import com.xns.xnsapp.adapter.WenzhangAdapter;
import com.xns.xnsapp.beans.Beixuan;
import com.xns.xnsapp.beans.Friend;
import com.xns.xnsapp.beans.Guanzhu;
import com.xns.xnsapp.beans.Huodong;
import com.xns.xnsapp.beans.HuodongItem;
import com.xns.xnsapp.beans.Item;
import com.xns.xnsapp.beans.Lesson_list;
import com.xns.xnsapp.beans.UserSimpleInfo;
import com.xns.xnsapp.beans.Wenzhang;
import com.xns.xnsapp.contants.UrlContants;
import com.xns.xnsapp.uis.ChildListView;
import com.xns.xnsapp.uis.RoundImageView;
import com.xns.xnsapp.utils.BaseApp;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by kinnyo-imac-24 on 15-6-18.
 */
public class HomePageActivity extends BaseActivity{
    private ActionBar mActionBar;
    //需要请求的URL
    private String meInfoUrl= UrlContants.getMeInfo();
    private String meWzUrl=UrlContants.getMeWzUrl();
    private String meGzUrl=UrlContants.getMeGzUrl();
    private String meBxUrl=UrlContants.getMeBxUrl();
    private String meHdUrl=UrlContants.getMeHdUrl();

    //需要携带的参数
    private String user_token="";
    private String user_id;
    private String page="0";

    //对应的数据源
    private ArrayList<Lesson_list> wzs =new ArrayList<Lesson_list>();
    private ArrayList<Friend> gzs=new ArrayList<Friend>();
    private ArrayList<Item> bxs=new ArrayList<Item>();
    private ArrayList<HuodongItem> hds=new ArrayList<HuodongItem>();

    //ActionBar对应的控件
    ImageView ivTitleBack;
    TextView tvTitleUserName;

    private RoundImageView roundUserIcon;
    private TextView tvUserName;
    private TextView tvUserDate;
    private ImageView ivBackground;
    private HttpUtils mHttpUtils;
    private BitmapUtils mBitmapUtils;

    private RadioGroup rgTabs;
    private TextView tvGrounp;
    //private RelativeLayout relative;

    private WenzhangAdapter wzAdapter;
    private GuanzhuAdapter gzAdapter;
    private BeixuanAdapter bxAdapter;
    private HuodongAdapter hdAdapter;

    //listview
    private ChildListView wzlv;
    private ChildListView gzlv;
    private ChildListView bxlv;
    private ChildListView hdlv;
    private ChildListView[] lvs;

    @Override
    public void initView() {
        //对ActionBar的初始化
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        mActionBar=getActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setCustomView(R.layout.activity_userhomepage_actionbar);
        View view=mActionBar.getCustomView();
        ivTitleBack=(ImageView)view.findViewById(R.id.iv_top_back);
        tvTitleUserName=(TextView)view.findViewById(R.id.tv_top_username);

        FadingActionBarHelper helper = new FadingActionBarHelper();
        helper.actionBarBackground(R.mipmap.title_background);
        helper.headerLayout(R.layout.activity_userhomepage_contentview);
        helper.contentLayout(R.layout.activity_userhomepage);
        setContentView(helper.createView(this));
        helper.initActionBar(this);
        ///////////////////////////

        ivBackground= (ImageView) findViewById(R.id.iv_background);
        roundUserIcon=(RoundImageView)findViewById(R.id.circle_usericon);
        tvUserName=(TextView)findViewById(R.id.tv_username);
        tvUserDate=(TextView)findViewById(R.id.tv_userdata);

        tvGrounp= (TextView) findViewById(R.id.tv_grounp);
        rgTabs= (RadioGroup) findViewById(R.id.rg_content);


        initListView();

        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        user_id=getIntent().getStringExtra("user_id");
        mHttpUtils=new HttpUtils();
        BaseApp baseApp=(BaseApp)getApplication();
        mBitmapUtils=baseApp.mBitmapUtils;
        mBitmapUtils.configDefaultLoadFailedImage(R.mipmap.default_homepage_background);
        mBitmapUtils.configDefaultLoadingImage(R.mipmap.default_homepage_background);

        //tabs点击监听
        rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_wenzhang:
                        tvGrounp.setText("全部文章（"+wzs.size()+"）");
                        showAndHide(0);
                        break;
                    case R.id.rb_guanzhu:
                        if(gzs.size()==0){
                            loadGzData();
                        }
                        tvGrounp.setText("全部关注（"+gzs.size()+"）");
                        showAndHide(1);
                        break;
                    case R.id.rb_beixuan:
                        if(bxs.size()==0){
                            loadBxData();
                        }
                        tvGrounp.setText("全部备注（"+bxs.size()+"）");
                        showAndHide(2);
                        break;
                    case R.id.rb_huodong:
                        if(hds.size()==0){
                            loadHdData();
                        }
                        tvGrounp.setText("全部活动（"+hds.size()+"）");
                        showAndHide(3);
                        break;
                }
            }
        });
    }

    //初始化对应的ListView
    private void initListView() {
        wzlv= (ChildListView) findViewById(R.id.lv_wz);
        gzlv= (ChildListView) findViewById(R.id.lv_gz);
        bxlv= (ChildListView) findViewById(R.id.lv_bx);
        hdlv= (ChildListView) findViewById(R.id.lv_hd);
        lvs=new ChildListView[]{wzlv,gzlv,bxlv,hdlv};

        wzAdapter=new WenzhangAdapter(this, wzs);
        gzAdapter=new GuanzhuAdapter(this,gzs);
        bxAdapter=new BeixuanAdapter(this,bxs);
        hdAdapter=new HuodongAdapter(this,hds);

        wzlv.setAdapter(wzAdapter);
        gzlv.setAdapter(gzAdapter);
        bxlv.setAdapter(bxAdapter);
        hdlv.setAdapter(hdAdapter);

        bxlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomePageActivity.this, "正在开发中", Toast.LENGTH_SHORT).show();
            }
        });

        hdlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomePageActivity.this,"正在开发中",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void loadData() {
        String postJson = "{" + "\"user_token\"" + ":" + "\"" + user_token + "\"" + ","
                + "" + "\"user_id\"" + ":" + "\"" + user_id + "\"" + "}";
        try {
            StringEntity se=new StringEntity(postJson);
            RequestParams params=new RequestParams();
            params.setBodyEntity(se);
            mHttpUtils.send(HttpRequest.HttpMethod.POST, meInfoUrl, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String text=responseInfo.result;
                    UserSimpleInfo userInfo= JSONObject.parseObject(text,UserSimpleInfo.class);
                    mBitmapUtils.display(roundUserIcon, userInfo.getAvatar());
                    mBitmapUtils.display(ivBackground,userInfo.getPoster());
                    tvTitleUserName.setText(userInfo.getNickname());
                    tvUserName.setText(userInfo.getNickname());
                    tvUserDate.setText(userInfo.getWedding_date());

                    //默认加载文章ListView
                    loadWzData();
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(HomePageActivity.this,"请求失败，请检查网络状况",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    // 显示/隐藏 ListView
    private void  showAndHide(int index){
       for(int i=0;i<lvs.length;i++){
           if(i==index){
               lvs[i].setVisibility(View.VISIBLE);
           }else {
               lvs[i].setVisibility(View.GONE);
           }
       }
    }


    //加载文章内容
    private void loadWzData() {
        String postJson = "{" + "\"user_token\"" + ":" + "\"" + user_token + "\"" + ","
                +  "\"user_id\"" + ":" + "\"" + user_id + "\"" +","+  "\"page\"" + ":" + "\"" + page + "\""+ "}";
        try {
            StringEntity se=new StringEntity(postJson);
            RequestParams params=new RequestParams();
            params.setBodyEntity(se);
            mHttpUtils.send(HttpRequest.HttpMethod.POST, meWzUrl, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String text=responseInfo.result;
                    Wenzhang wenzhang= JSONObject.parseObject(text, Wenzhang.class);
                    if(Integer.parseInt(wenzhang.getTotal_count())>0){
                        wzs.clear();
                        wzs.addAll(wenzhang.getLesson_list());
                        wzAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(HomePageActivity.this, "请求失败，请检查网络状况", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //加载关注数据
    private void loadGzData(){
        String postJson = "{" + "\"user_token\"" + ":" + "\"" + user_token + "\"" + ","
                +  "\"user_id\"" + ":" + "\"" + user_id + "\"" +","+  "\"page\"" + ":" + "\"" + page + "\""+ "}";
        try {
            StringEntity se=new StringEntity(postJson);
            RequestParams params=new RequestParams();
            params.setBodyEntity(se);
            mHttpUtils.send(HttpRequest.HttpMethod.POST, meGzUrl, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String text=responseInfo.result;
                    Guanzhu guanzhu= JSONObject.parseObject(text, Guanzhu.class);
                    if(Integer.parseInt(guanzhu.getTotal_count())>0){
                        gzs.clear();
                        gzs.addAll(guanzhu.getFriends());
                        gzAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(HomePageActivity.this, "请求失败，请检查网络状况", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //加载备选数据
    private void loadBxData(){
        String postJson = "{" + "\"user_token\"" + ":" + "\"" + user_token + "\"" + ","
                +  "\"user_id\"" + ":" + "\"" + user_id + "\"" +","+  "\"page\"" + ":" + "\"" + page + "\""+ "}";
        try {
            StringEntity se=new StringEntity(postJson);
            RequestParams params=new RequestParams();
            params.setBodyEntity(se);
            mHttpUtils.send(HttpRequest.HttpMethod.POST, meBxUrl, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String text=responseInfo.result;
                    Beixuan beixuan= JSONObject.parseObject(text, Beixuan.class);
                    if(Integer.parseInt(beixuan.getTotal_count())>0){
                        bxs.clear();
                        bxs.addAll(beixuan.getItems());
                        bxAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(HomePageActivity.this, "请求失败，请检查网络状况", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //加载活动数据
    private void loadHdData(){
        String postJson = "{" + "\"user_token\"" + ":" + "\"" + user_token + "\"" + ","
                +  "\"user_id\"" + ":" + "\"" + user_id + "\"" +","+  "\"page\"" + ":" + "\"" + page + "\""+ "}";
        try {
            StringEntity se=new StringEntity(postJson);
            RequestParams params=new RequestParams();
            params.setBodyEntity(se);
            mHttpUtils.send(HttpRequest.HttpMethod.POST, meHdUrl, params, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String text=responseInfo.result;
                    Huodong huodong= JSONObject.parseObject(text, Huodong.class);
                    if(Integer.parseInt(huodong.getTotal_count())>0){
                        hds.clear();
                        hds.addAll(huodong.getItems());
                        hdAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(HomePageActivity.this, "请求失败，请检查网络状况", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
