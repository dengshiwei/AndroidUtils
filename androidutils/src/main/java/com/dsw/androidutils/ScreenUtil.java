package com.dsw.androidutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 屏幕工具类，涉及到屏幕宽度、高度、密度比、(像素、dp、sp)之间的转换等。
 * @author Administrator
 *
 */
public class ScreenUtil {
	private ScreenUtil(){};
	
	/**
	 * 获取屏幕宽度，单位为px
	 * @param context	应用程序上下文
	 * @return
	 * 		屏幕宽度，单位px
	 */
	public static int getScreenWidth(Context context){
		return getDisplayMetrics(context).widthPixels;
	}
	
	/**
	 * 获取屏幕高度，单位为px
	 * @param context	应用程序上下文
	 * @return
	 * 		屏幕高度，单位px
	 */
	public static int getScreenHeight(Context context){
		return getDisplayMetrics(context).heightPixels;
	}
	
	/**
	 * 获取系统dp尺寸密度值
	 * @param context	应用程序上下文
	 * @return
	 */
	public static float getDensity(Context context){
		return getDisplayMetrics(context).density;
	}
	
	/**
	 * 获取系统字体sp密度值
	 * @param context	应用程序上下文
	 * @return
	 */
	public static float getScaledDensity(Context context){
		return getDisplayMetrics(context).scaledDensity;
	}
	
	/**
	 * dip转换为px大小
	 * @param context	应用程序上下文
	 * @param dpValue	dp值
	 * @return	转换后的px值
	 */
	public static int dp2px(Context context, int dpValue){
		return (int) (dpValue * getDensity(context) + 0.5f);
	}
	
	/**
	 * px转换为dp值
	 * @param context	应用程序上下文
	 * @param pxValue	px值
	 * @return	转换后的dp值
	 */
	public static int px2dp(Context context, int pxValue){
		return (int) (pxValue / getDensity(context) + 0.5f);
	}
	
	/**
	 * sp转换为px
	 * @param context	应用程序上下文
	 * @param spValue	sp值
	 * @return		转换后的px值
	 */
	public static int sp2px(Context context, int spValue){
		return (int) (spValue * getScaledDensity(context) + 0.5f);
	}
	
	/**
	 * px转换为sp
	 * @param context	应用程序上下文
	 * @param pxValue	px值
	 * @return	转换后的sp值
	 */
	public static int px2sp(Context context, int pxValue){
		return (int) (pxValue / getScaledDensity(context) + 0.5f);
	}
	
	
	  /** 
     * 获得状态栏的高度 
     *  
     * @param context 
     * @return 
     */  
    public static int getStatusHeight(Context context){
        int statusHeight = -1;  
        try{  
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");  
            Object object = clazz.newInstance();  
            int height = Integer.parseInt(clazz.getField("status_bar_height")  
                    .get(object).toString());  
            statusHeight = context.getResources().getDimensionPixelSize(height);  
        } catch (Exception e){  
            e.printStackTrace();  
        }  
        return statusHeight;  
    } 
	
    /** 
     * 获取当前屏幕截图，包含状态栏 
     * @param activity 
     * @return 
     */  
    public static Bitmap snapShotWithStatusBar(Activity activity){  
        View decorView = activity.getWindow().getDecorView();  
        decorView.setDrawingCacheEnabled(true);  
        decorView.buildDrawingCache();  
        Bitmap bmp = decorView.getDrawingCache();  
        int width = getScreenWidth(activity);  
        int height = getScreenHeight(activity);  
        Bitmap bitMap = null;  
        bitMap = Bitmap.createBitmap(bmp, 0, 0, width, height);  
        decorView.destroyDrawingCache();  
        return bitMap;
    } 
    
    /** 
     * 获取当前屏幕截图，不包含状态栏 
     * @param activity 
     * @return 
     */  
    public static Bitmap snapShotWithoutStatusBar(Activity activity){  
        View decorView = activity.getWindow().getDecorView();  
        decorView.setDrawingCacheEnabled(true);  
        decorView.buildDrawingCache();  
        Bitmap bmp = decorView.getDrawingCache();  
        Rect frame = new Rect();  
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
        int statusBarHeight = frame.top;  
  
        int width = getScreenWidth(activity);  
        int height = getScreenHeight(activity);  
        Bitmap bitMap = null;  
        bitMap = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height  
                - statusBarHeight);  
        decorView.destroyDrawingCache();  
        return bitMap;
    } 
    
	/**
	 * 获取DisplayMetrics对象
	 * @param context	应用程序上下文
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context){
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics;
	}
}
