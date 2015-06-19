package com.xns.xnsapp.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xns.xnsapp.R;
import com.xns.xnsapp.fragment.ChatFragment;
import com.xns.xnsapp.fragment.FindFragment;
import com.xns.xnsapp.fragment.MainFragment;
import com.xns.xnsapp.fragment.MeFragment;
import com.xns.xnsapp.uis.ArcMenu;


public class MainActivity extends BaseActivity {
	@ViewInject(R.id.linear_container)
	private LinearLayout linear_container;

	@ViewInject(R.id.rg_tabs)
	private RadioGroup rg_tabs;
	
	@ViewInject(R.id.id_arcmenu1)
	private ArcMenu mArcMenu;

	private MainFragment mainFragment;
	private FindFragment findFragment;
	private ChatFragment chatFragment;
	private MeFragment meFragment;

	//actionbar
	private ActionBar mActionBar;

	// 四个Fragment的数组，方便对Fragment的管理
	private Fragment fragments[] = new Fragment[]{mainFragment,findFragment,chatFragment,meFragment};
	// 初始化话视图
	@Override
	public void initView() {
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		mActionBar=getActionBar();
		mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		mActionBar.setCustomView(R.layout.title_main);
		View titleView=mActionBar.getCustomView();
		ImageView ivSearch=(ImageView) titleView.findViewById(R.id.iv_top_search);
		ivSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,SearchActivity.class);
				startActivity(intent);
			}
		});
		// 进入app,先加载第一个Fragment.
		mainFragment = new MainFragment();
		fragments[0] = mainFragment;
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.linear_container, mainFragment);
		ft.commit();
		//对ArcMenu的子项的点击监听
		mArcMenu.setOnMenuItemClickListener(new ArcMenu.OnMenuItemClickListener() {
			
			@Override
			public void onClick(View view, int pos) {
				Toast.makeText(MainActivity.this,"点击了："+pos+"个！", Toast.LENGTH_SHORT).show();
			}
		});
	}

	// 加载数据
	@Override
	public void loadData() {
		// TODO Auto-generated method stub

	}
	

	// RadioButton的点击监听
	public void rbClick(View view) {
		View titleView=mActionBar.getCustomView();
		switch (view.getId()) {
		case R.id.rb_main:
			mActionBar.setCustomView(R.layout.title_main);
			titleView=mActionBar.getCustomView();
			ImageView ivSearch=(ImageView) titleView.findViewById(R.id.iv_top_search);
			ivSearch.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(MainActivity.this,SearchActivity.class);
					startActivity(intent);
				}
			});

			// 主页
			showFragment(0);
			hideFragment(0);
			break;
		case R.id.rb_find:
			mActionBar.setCustomView(R.layout.title_find);
			titleView=mActionBar.getCustomView();
			ImageView ivSearchfind=(ImageView) titleView.findViewById(R.id.iv_top_search);
			ivSearchfind.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});

			
			if (fragments[1] != null) {
				showFragment(1);
			} else {
				FragmentTransaction ft = fm.beginTransaction();
				findFragment = new FindFragment();
				fragments[1] = findFragment;
				ft.add(R.id.linear_container, findFragment);
				ft.commit();
			}
			hideFragment(1);
			break;
		case R.id.rb_chat:
			mActionBar.setCustomView(R.layout.title_chat);
			titleView=mActionBar.getCustomView();
			ImageView ivAddfriends=(ImageView) titleView.findViewById(R.id.iv_top_friend);
			ivAddfriends.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
			// 聊天
			if (fragments[2] != null) {
				showFragment(2);
			} else {
				FragmentTransaction ft = fm.beginTransaction();
				chatFragment = new ChatFragment();
				fragments[2] = chatFragment;
				ft.add(R.id.linear_container, chatFragment);
				ft.commit();
			}
			hideFragment(2);
			break;
		case R.id.rb_me:
			mActionBar.setCustomView(R.layout.title_me);
			titleView=mActionBar.getCustomView();
			ImageView ivSetting=(ImageView) titleView.findViewById(R.id.iv_top_setting);
			ivSetting.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});

			ImageView ivNotice=(ImageView) titleView.findViewById(R.id.iv_top_notice);
			ivNotice.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});
			// 我
			if (fragments[3] != null) {
				showFragment(3);
			} else {
				FragmentTransaction ft = fm.beginTransaction();
				meFragment = new MeFragment();
				fragments[3] = meFragment;
				ft.add(R.id.linear_container, meFragment);
				ft.commit();
			}
			hideFragment(3);
			break;
		}
	}

	// 显示Fragment
	private void showFragment(int index) {
		FragmentTransaction ft = fm.beginTransaction();
		ft.show(fragments[index]);
		ft.commit();
	}

	// 隐藏Fragment
	private void hideFragment(int index) {
		for (int i = 0; i < fragments.length; i++) {
			if (i != index && fragments[i] != null) {
				FragmentTransaction ft = fm.beginTransaction();
				ft.hide(fragments[i]);
				ft.commit();
			}
		}
	}

}
