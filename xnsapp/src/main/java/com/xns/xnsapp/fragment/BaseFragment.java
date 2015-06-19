package com.xns.xnsapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 所有Fragment的基类，对Http请求、图片的加载和缓存进行管理，
 * @author XNS02
 *
 */
public abstract class BaseFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=initView(inflater, container, savedInstanceState);
		loadData();
		return view;
	}
	
	/**
	 * 初始化视图的抽象方法
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	public abstract View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);
		
	public abstract void loadData();
}
