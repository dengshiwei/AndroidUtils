package com.dsw.androidutils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import android.content.ContentValues;

import com.dsw.androidutils.database.AnnotationColumn;

public class CommonUtil {
	
	/**
	 * 将对象转换成ContentValues对象
	 * @param object	对象
	 * @return		返回转换后的ContentValues对象
	 */
	public static ContentValues objectToContentValues(Object object){
		if(object == null){
			throw new IllegalArgumentException("please check your argument");
		}
		ContentValues contentValues = new ContentValues();
		try{
			Field[] fields = object.getClass().getDeclaredFields();
			for(Field field : fields){
				String fieldName = field.getName();
				Type type = field.getType();
				field.setAccessible(true);
				if(type.equals(String.class)){
					contentValues.put(fieldName, field.get(object).toString());
				}else if(type.equals(Integer.class) || type.equals(Integer.TYPE)){
					contentValues.put(fieldName, field.getInt(object));
				}else if(type.equals(Float.class) || type.equals(Float.TYPE)){
					contentValues.put(fieldName, field.getFloat(object));
				}else if(type.equals(Long.class) || type.equals(Long.TYPE)){
					contentValues.put(fieldName, field.getLong(object));
				}else if(type.equals(Double.class) || type.equals(Double.TYPE)){
					contentValues.put(fieldName, field.getDouble(object));
				}else if(type.equals(Boolean.class) || type.equals(Boolean.TYPE)){
					contentValues.put(fieldName, field.getBoolean(object));
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return contentValues;
	}
	
	/**
	 * 将对象中注解为转换成ContentValues对象
	 * @param object	Object对象
	 * @return
	 */
	public static ContentValues objAnnotToContValues(Object object){
		if(object == null){
			throw new IllegalArgumentException("please check your argument");
		}
		ContentValues contentValues = new ContentValues();
		try{
			Field[] fields = object.getClass().getDeclaredFields();
			for(Field field : fields){
				if(!field.isAnnotationPresent(AnnotationColumn.class))continue;
				String fieldName = field.getName();
				Type type = field.getType();
				field.setAccessible(true);
				if(type.equals(String.class)){
					contentValues.put(fieldName, field.get(object).toString());
				}else if(type.equals(Integer.class) || type.equals(Integer.TYPE)){
					contentValues.put(fieldName, field.getInt(object));
				}else if(type.equals(Float.class) || type.equals(Float.TYPE)){
					contentValues.put(fieldName, field.getFloat(object));
				}else if(type.equals(Long.class) || type.equals(Long.TYPE)){
					contentValues.put(fieldName, field.getLong(object));
				}else if(type.equals(Double.class) || type.equals(Double.TYPE)){
					contentValues.put(fieldName, field.getDouble(object));
				}else if(type.equals(Boolean.class) || type.equals(Boolean.TYPE)){
					contentValues.put(fieldName, field.getBoolean(object));
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return contentValues;
	}
}
