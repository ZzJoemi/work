package com.xns.xnsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xns.xnsapp.R;

public class ChatFragment extends BaseFragment {

	@Override
	public View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_chat, null);
		return view;
	}

	public void loadData() {
		// TODO Auto-generated method stub

	}

}
