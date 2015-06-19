package com.xns.xnsapp.adapter;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.xns.xnsapp.utils.BaseApp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
	
	public Context context;
	/**
	 * 数据源集合
	 */
	public List<T> list;
	/**
	 * 图片加载
	 */
	public BitmapUtils mBitmapUtils;
	
	public MyBaseAdapter(Context context, List<T> list) {
		this.context = context;
		this.list = list;
		BaseApp baseApp=(BaseApp)context.getApplicationContext();
		mBitmapUtils=baseApp.mBitmapUtils;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return initItemView(position, convertView,arg2);
	}
	
	/**
	 * 初始化子项视图
	 */
	public abstract View initItemView(int position, View convertView, ViewGroup arg2);
	
}
