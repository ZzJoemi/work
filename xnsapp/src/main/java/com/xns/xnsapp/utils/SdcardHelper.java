package com.xns.xnsapp.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.os.StatFs;

public class SdcardHelper {
	/**
	 * 判断SD卡是否正确挂载
	 * @return
	 */
	public static  boolean isSdCardMounted(){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取SD卡路径
	 * @return
	 */
	public static File getSdCardFilePath(){
		if(isSdCardMounted()){
			return Environment.getExternalStorageDirectory();
		}
		return null;
	}
	
	public static String getSdCardAbsolutePath(){
		if(isSdCardMounted()){
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * 查看SD卡大小
	 */
	public static String getSdCardAllRoom(){
		StatFs statfs=new StatFs(getSdCardAbsolutePath());
		long count=statfs.getBlockCount();
		long size=statfs.getBlockSize();
		return count*size/1024/1024+"MB";
	}
	
	public static String getSdCardAllRoomPro(){
		StatFs statfs=new StatFs(getSdCardAbsolutePath());
		//API14以上版本getBlockCountLong
		long count=statfs.getAvailableBlocks();
		long size=statfs.getBlockSize();
		return count*size/1024/1024+"MB";
	}
	
	/**
	 * 返回公共路径
	 */
	public static String getSdCardPublicPath(String path){
		if(isSdCardMounted()){
			return Environment.getExternalStoragePublicDirectory(path).getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * 返回data/data目录
	 */
	public static String getDataAbsolutePath(){
		return Environment.getDataDirectory().getAbsolutePath();
	}
	
	/**
	 * 写入Sd卡
	 * @return 返回写入是否成功
	 */
	public static boolean writeToSdCard(String dir,String fileName,byte[] data){
		File file=new File(getSdCardAbsolutePath()+File.separator+dir);
		if(!file.exists()){
			file.mkdirs();
		}
		File f=new File(file.getAbsoluteFile(),fileName);
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(f);
			fos.write(data);
			fos.flush();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;		
	}
	
	/**
	 * 读取数据
	 * @return
	 */
	public static byte[] readOnSdCard(String filePath){
		File file=new File(filePath);
		FileInputStream fis=null;
		ByteArrayOutputStream baos=null;
		byte data[]=null;
		try {
			fis=new FileInputStream(file);
			baos=new ByteArrayOutputStream();
			int len=0;
			byte buffer[]=new byte[1024];
			while((len=fis.read(buffer))!=-1){
				baos.write(buffer, 0, len);
				baos.flush();
			}
			data=baos.toByteArray();
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(fis!=null){
					fis.close();
				}
				if(baos!=null){
					baos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
