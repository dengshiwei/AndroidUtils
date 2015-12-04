package com.dsw.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;

/**
 * Bitmap工具类，获取Bitmap对象
 * @author Administrator
 *
 */
public class BitmapUtil {
	
	private BitmapUtil(){};
		
	/**
	 * 根据资源id获取指定大小的Bitmap对象
	 * @param context	应用程序上下文
	 * @param id		资源id
	 * @param height	高度
	 * @param width		宽度
	 * @return
	 */
	public static Bitmap getBitmapFromResource(Context context,int id,int height,int width){
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), id, options);
		options.inSampleSize = calculateSampleSize(height, width, options);
		options.inJustDecodeBounds = false;
		Bitmap bitmap;
		bitmap = BitmapFactory.decodeResource(context.getResources(), id, options);
		return bitmap;
	}
	
	/**
	 * 根据文件路径获取指定大小的Bitmap对象
	 * @param path		文件路径
	 * @param height	高度
	 * @param width		宽度
	 * @return
	 */
	public static Bitmap getBitmapFromFile(String path, int height, int width){
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		options.inSampleSize = calculateSampleSize(height, width, options);
		options.inJustDecodeBounds = false;
		Bitmap bitmap;
		bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}
	
	/**
	 * 获取指定大小的Bitmap对象
	 * @param bitmap	Bitmap对象
	 * @param height	高度
	 * @param width		宽度
	 * @return
	 */
	public static Bitmap getThumbnailsBitmap(Bitmap bitmap, int height, int width){
		return ThumbnailUtils.extractThumbnail(bitmap, width, height);
	}

	/**
	 * 计算所需图片的缩放比例
	 * @param height	高度
	 * @param width		宽度
	 * @param options	options选项
	 * @return
	 */
	private static int calculateSampleSize(int height, int width, Options options) {
		int realHeight = options.outHeight;
		int realWidth = options.outWidth;
		int heigthScale = realHeight / height;
		int widthScale = realWidth / width;
		if(widthScale > heigthScale){
			return widthScale;
		}else{
			return heigthScale;
		}
	}
}
