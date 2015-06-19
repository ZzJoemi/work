package com.xns.xnsapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.xns.xnsapp.R;


public class WelcomeActivity extends BaseActivity {
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			//跳转到主界面
			if(msg.what==0x01){
				Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
		
	};
	
	@Override
	public void initView() {
		setContentView(R.layout.activity_welcome);
		//开线程，欢迎界面在1秒后发消息跳转到主界面
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					handler.sendEmptyMessage(0x01);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub

	}

}
