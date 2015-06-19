package com.xns.xnsapp.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xns.xnsapp.R;


public class SearchActivity extends BaseActivity {
	private ActionBar mActionBar;
	@Override
	public void initView() {
		overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
		setContentView(R.layout.activity_search);
		ViewUtils.inject(this);
		mActionBar=getActionBar();
		mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		mActionBar.setCustomView(R.layout.title_search);
		View titleView=mActionBar.getCustomView();
		ImageView ivBack=(ImageView) titleView.findViewById(R.id.iv_top_back);
		ivBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
			}
		});
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub

	}
	
}
