package com.xns.xnsapp.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public abstract class BaseActivity extends FragmentActivity {
	/**
	 * 获取fragment管理类
	 */
	public FragmentManager fm;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		fm=getSupportFragmentManager();
		initView();
		loadData();
	}
	
	/**
	 * 抽象方法，所有继承该基类的Activity都需要去重写，初始化视图
	 */
	public abstract void initView();
	
	/**
	 * 加载数据
	 */
	public abstract void loadData();
	
}
