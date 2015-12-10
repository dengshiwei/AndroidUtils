package com.dsw.androidutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * 网络工具类，包含网络的判断、跳转到设置页面
 * @author Administrator
 *
 */
public class NetWorkUtil {

	/**
	 * 判断当前是否有网络连接
	 * @param context
	 * @return	有网络返回true；无网络返回false
	 */
	public static boolean isNetWorkEnable(Context context){
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if(networkInfo != null && networkInfo.isConnected()){
			if(networkInfo.getState() == NetworkInfo.State.CONNECTED){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断当前网络是否为wifi
	 * @param context
	 * @return	如果为wifi返回true；否则返回false
	 */
	public static boolean isWiFiConnected(Context context){
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo.getType() == ConnectivityManager.TYPE_WIFI ? true :false;
	}
	
	/**
	 * 判断MOBILE网络是否可用
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static boolean isMobileDataEnable(Context context){
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean isMobileDataEnable = false;
		isMobileDataEnable = connectivityManager.getNetworkInfo(
				ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

		return isMobileDataEnable;
	}

	/**
	 * 判断wifi 是否可用
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static boolean isWifiDataEnable(Context context){
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean isWifiDataEnable = false;
		isWifiDataEnable = connectivityManager.getNetworkInfo(
				ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		return isWifiDataEnable;
	}
	
	/**
	 * 跳转到网络设置页面
	 * @param activity
	 */
	public static void GoSetting(Activity activity){
		 Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
		 activity.startActivity(intent);
	}
}
