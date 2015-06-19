package com.xns.xnsapp.fragment;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullStickyListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xns.xnsapp.R;

import com.xns.xnsapp.adapter.StickyListAdapter;
import com.xns.xnsapp.beans.Home;
import com.xns.xnsapp.beans.Lesson_list;
import com.xns.xnsapp.contants.UrlContants;


import org.apache.http.entity.StringEntity;



public class MainFragment extends BaseFragment{

	private PullStickyListView psl;
	private LinearLayout linearLoad;

	private HttpUtils mHttpUtils;
	private HttpURLConnection uRLConnection;
	private String url = UrlContants.getMainList();
	//private PslvAdapter adapter;
	private StickyListAdapter stadapter;


	//数据源
	private List<Lesson_list> lesson_lists;
	private Home home;

	//加载数据时需要的参数
	private String user_token;
	private int page;
	// 加载布局，进行视图初始化
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, null);
		
		psl=(PullStickyListView)view.findViewById(R.id.stickylistview);

		psl.setMode(PullToRefreshBase.Mode.BOTH);
		linearLoad=(LinearLayout)view.findViewById(R.id.linear_load);
		
		lesson_lists=new ArrayList<Lesson_list>();

		mHttpUtils = new HttpUtils();
		//
		user_token="";
		page=0;
		return view;
	}
	
	//加载数据
	@Override
	public void loadData() {
		//String jsonStr="{"+"'user_token:'"+user_token+","+"'page:'"+page+"}";
		String postJson = "{" + "\"user_token\"" + ":" + "\"" + user_token + "\"" + ","
				+ "" + "\"page\"" + ":" + "\"" + page + "\"" + "}";
		try {
			StringEntity se = new StringEntity(postJson);
			RequestParams params = new RequestParams();
			params.setBodyEntity(se);
			mHttpUtils.send(HttpMethod.POST, url, params,
					new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							Toast.makeText(getActivity(), "获取数据失败,请检查网络状况", Toast.LENGTH_SHORT).show();
							Log.i("MainFragment", "获取数据失败");
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							String text = arg0.result;
							Log.i("MainFragment", text);
							home = JSONObject.parseObject(text, Home.class);
							lesson_lists.addAll(home.getLesson_list());
							//adapter = new PslvAdapter(MainFragment.this.getActivity(), lesson_lists);
							//lvRefreshpinned.setAdapter(adapter);
							linearLoad.setVisibility(View.GONE);
							stadapter=new StickyListAdapter(MainFragment.this.getActivity(), lesson_lists);
							psl.setAdapter(stadapter);
						}
					});
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
