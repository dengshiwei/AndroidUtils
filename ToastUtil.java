package com.dsw.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * @author Administrator
 *
 */
public class ToastUtil {
	
	private ToastUtil(){};
	
	/**
	 * 长时Toast
	 * @param context
	 * @param msg
	 */
	public static void showLongToast(Context context, String msg){
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * 短时Toast
	 * @param context
	 * @param msg
	 */
	public static void showShortToast(Context context, String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
