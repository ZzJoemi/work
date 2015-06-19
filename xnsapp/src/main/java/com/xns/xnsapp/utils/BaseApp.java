package com.xns.xnsapp.utils;
import java.io.File;

import com.lidroid.xutils.BitmapUtils;

import android.app.Application;
import android.widget.Toast;


/**
 * 应该要处理以下问题：
 * 1.网络监听
 * 2.第三方的一些开源库
 * 3.数据库的配置
 * 4.图片框架配置
 * 5.全局的异常的处理
 * 6.全局的Log日志控制
 */
public class BaseApp extends Application {
	private static String packageName="com.xns.xnsapp";
	private static String cacheName="imgCache";
    private static BaseApp app;
    public BitmapUtils mBitmapUtils;

    public static BaseApp getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String imgCachePath="";
        if(SdcardHelper.isSdCardMounted()){
        	imgCachePath=SdcardHelper.getSdCardAbsolutePath()+File.separator+packageName+File.separator+cacheName;
        }else{
        	imgCachePath=SdcardHelper.getDataAbsolutePath()+File.separator+packageName+File.separator+cacheName;
        }
        mBitmapUtils=new BitmapUtils(getApplicationContext(), imgCachePath);
       
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
