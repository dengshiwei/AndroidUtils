package com.dsw.androidutils.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.dsw.androidutils.LogUtil;
import com.dsw.androidutils.ReflectUtil;


/**
 * 数据库工具类，包含数据库的创建、表的创建、增删改查
 * @author Administrator
 *
 */
public class DataBaseHelper {
	private static final String TAG = DataBaseHelper.class.getSimpleName();
	private SQLiteHelper mSQlLiteHelper;
	private SQLiteDatabase mSqLiteDatabaseReadable,mSqLiteDatabaseWritable;
	
	/**
	 * 在构造函数中创建数据库，方便下次使用
	 * @param context	应用程序上下文
	 * @param dataBaseName		数据库名称
	 * @param version			数据库版本
	 */
	public DataBaseHelper(Context context,String dataBaseName, int version){
		if(context == null || TextUtils.isEmpty(dataBaseName) || version < 1){
			throw new IllegalArgumentException("please check your params,some params is null | empty or version code <1");
		}
		mSQlLiteHelper = new SQLiteHelper(context, dataBaseName, null, version);
		mSqLiteDatabaseReadable = mSQlLiteHelper.getReadableDatabase();
		mSqLiteDatabaseWritable = mSQlLiteHelper.getWritableDatabase();
	};
	
	public SQLiteDatabase getmSqLiteDatabaseReadable() {
		return mSqLiteDatabaseReadable;
	}

	public SQLiteDatabase getmSqLiteDatabaseWritable() {
		return mSqLiteDatabaseWritable;
	}

	public SQLiteHelper getmSQlLiteHelper() {
		return mSQlLiteHelper;
	}
	
	/**
	 * 创建数据表,该套逻辑是基于标注解和字段注解进行判断，由于在开发过程中，我们需要创建实体，
	 * 所以只需要在对应的字段上加上注解就可以了。
	 * @param tableClassList  需要创建表的实体类集合
	 */
	public void createTables(List<Class<?>> tableClassList){
		if(tableClassList == null || tableClassList.size() == 0){
			throw new IllegalArgumentException("please check your params,some params is null or empty");
		}
		
		for(Class<?> clazz : tableClassList){
	         StringBuilder sbCreateSQL = new StringBuilder();
	         sbCreateSQL.append("CREATE TABLE IF NOT EXISTS ");
	         if(clazz.isAnnotationPresent(AnnotationTable.class)){//判断是否为AnotationTable注解类型
	             //获取AnnotationTable注解
	             AnnotationTable tableAnno = clazz.getAnnotation(AnnotationTable.class);
	             //获取AnnotatbleTable注解的属性值，即表名
	             String tableName = tableAnno.name();
	             sbCreateSQL.append(tableName + "(");
	             //通过反射获取Person实体的成员变量，判断哪些是注解要被创建成Person表中的字段
	             Field[] fields = clazz.getDeclaredFields();
	             for(int i=0;i<fields.length;i++){
	                 Field field = fields[i];
	                 //判断字段是否为注解字段
	                 if(field.isAnnotationPresent(AnnotationColumn.class)){
	                     AnnotationColumn columnAnno = field.getAnnotation(AnnotationColumn.class);
	                     String fieldName = columnAnno.name();
	                     String fieldNull = columnAnno.isNull() ? "" : "NOT NULL ";
	                     String fieldKey = columnAnno.isPrimaryKey() ?  "PRIMARY KEY" :"" ;
	                     String fieldType = columnAnno.type();
	                     sbCreateSQL.append(fieldName + " " +fieldType + " "+ fieldKey + " " + fieldNull + ",");
	                 }
	             }
	             sbCreateSQL.replace(sbCreateSQL.length() -1, sbCreateSQL.length(), ")");
	             Log.d(TAG,sbCreateSQL.toString());
	             //创建表
	             mSqLiteDatabaseWritable.execSQL(sbCreateSQL.toString());
	         }
	     }
	}
	
	/**
	 * 向数据库插入数据
	 * @param tableName 	插入数据表名
	 * @param contentValues		插入数据的值
	 * @return	正常返回最后一条数据ID，错误返回-1
	 */
	public long insert(String tableName, ContentValues contentValues){
		if(TextUtils.isEmpty(tableName) || contentValues == null){
			throw new IllegalArgumentException("please check your params,some params is null or empty");
		}
		
		return mSqLiteDatabaseWritable.insert(tableName, null, contentValues);
	}
	
	/**
	 * 通过SQL语句进行插入数据
	 * @param sqlInsert		SQL语句
	 */
	public void insertBySQL(String sqlInsert){
		executeSQL(sqlInsert);
	}
	
	/**
	 * 更新数据信息，参数信息update(sqLiteHelper,"T_User", contentValues,
	 * "  User_ID=? and Age=?", new String[] { String.valueOf(1022),String.valueOf(30)});
	 * @param tableName		表名
	 * @param contentValues		数据值
	 * @param whereClause		条件语句
	 * @param whereArgs			条件值
	 * @return	返回影响的记录数	
	 */
	public int update(String tableName, ContentValues contentValues,
								String whereClause, String [] whereArgs){
		if(TextUtils.isEmpty(tableName) || contentValues == null){
			throw new IllegalArgumentException("please check your params,some params is null or empty");
		}
		return mSqLiteDatabaseWritable.update(tableName, contentValues, whereClause, whereArgs);
	}
	
	/**
	 * 通过SQL语句进行数据库的更新
	 * @param sqlUpdate		sql语句
	 */
	public void updateBySQL(String sqlUpdate){
		executeSQL(sqlUpdate);
	}
	
	/**
	 * 删除数据,delete(sqLiteHelper,"T_User","  User_ID=? and Age=?", new String[] { String.valueOf(1022),String.valueOf(30)});
	 * @param tableName		表名
	 * @param whereClause	条件语句
	 * @param whereArgs		条件值
	 * @return	如果传递whereClause返回影响的记录数，没传递返回0；如果删除所有数据，用"1"当做whereClause
	 */
	public int delete(String tableName, String whereClause, String [] whereArgs){
		if(TextUtils.isEmpty(tableName)){
			throw new IllegalArgumentException("please check your params,some params is null or empty");
		}
		
		return mSqLiteDatabaseWritable.delete(tableName, whereClause, whereArgs);
	}
	
	/**
	 * 通过SQL语句进行数据的删除
	 * @param sqlUpdate		SQL语句
	 */
	public void deleteBySQL(String sqlUpdate){
		executeSQL(sqlUpdate);
	}
	
	/**
	 * 查询,queryCursor(sqLiteHelper,"SELECT * FROM table WHERE name=?", new String[]("android"));
	 * @param sql	条件语句
	 * @param whereArgs		条件语句值
	 * @return	返回查询结果游标
	 */
	public Cursor queryCursor(String sql, String [] whereArgs){
		if(TextUtils.isEmpty(sql)){
			throw new IllegalArgumentException("please check your sql:" + sql);
		}
		return mSqLiteDatabaseReadable.rawQuery(sql, whereArgs);
	}
	
	/**
	 * 查询数据库，返回集合。queryList(sqLiteHelper,"SELECT * FROM table WHERE name=?", new String[]("android"), table.class);
	 * @param sql	条件语句
	 * @param whereArgs		条件语句值
	 * @return 返回查询结果的数据集合
	 */
	public <T> List<T> queryList(String sql, String[] whereArgs, Class<T> clazz ){
		
		Cursor cursor = queryCursor(sql, whereArgs);
		return cursorToList(cursor, clazz);
	}
	
	/**
	 * 查询第一个记录，queryFirst(sqLiteHelper,"SELECT * FROM table WHERE name=?", new String[]("android"), table.class);
	 * @param sql			查询SQL语句
	 * @param whereArgs		查询参数
	 * @param clazz			实体Class
	 * @return	返回对应实体
	 */
	public <T> T queryFirst(String sql, String[] whereArgs, Class<T> clazz){
		if(TextUtils.isEmpty(sql) || clazz == null){
			throw new IllegalArgumentException("please check your params,some params is null or empty");
		}
		Cursor cursor = queryCursor(sql, whereArgs);
		List<T> list = cursorToList(cursor, clazz);
		if(list != null && list.size() >0)return list.get(0);
		return null;
	}
	
	/**
	 * 开启事务
	 */
	public void beginTranslaction(){
		mSqLiteDatabaseReadable.beginTransaction();
		mSqLiteDatabaseWritable.beginTransaction();
	}
	
	/**
	 * 设置事务处理成功
	 */
	public void setTransactionSuccessful(){
		mSqLiteDatabaseReadable.setTransactionSuccessful();
		mSqLiteDatabaseWritable.setTransactionSuccessful();
	}
	
	/**
	 * 设置事务处理结束
	 */
	public void endTransaction(){
		mSqLiteDatabaseReadable.endTransaction();
		mSqLiteDatabaseWritable.endTransaction();
	}
	
	/**
	 * 获取某个表中的记录条数
	 * @param tableName	表名称
	 * @return		返回查询的记录条数，查询异常，则返回0
	 */
	public int getCountOfTable(String tableName){
		if(TextUtils.isEmpty(tableName)){
			throw new IllegalArgumentException("please check you param,param is null or empty");
		}
		
		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append("SELECT COUNT(*) FROM ")
		.append(tableName);
		Cursor cursor = queryCursor(sbSQL.toString(), null);
		return cursor == null ? 0: cursor.getCount();
	}
	
	/**
	 * 根据SQL语句，获取查询结果的条数
	 * @param sql		SQL语句
	 * @param whereArgs		对应参数的值
	 * @return		返回查询的记录条数，查询异常，则返回0
	 */
	public int getCount(String sql,String[] whereArgs){
		Cursor cursor = queryCursor(sql, whereArgs);
		return cursor == null ? 0: cursor.getCount();
	}
	
	/**
	 * 删除表，从数据库中删除该表
	 * @param tableName	表名
	 */
	public void dropTable(String tableName){
		if(TextUtils.isEmpty(tableName)){
			throw new IllegalArgumentException("please check you param,param is null or empty");
		}
		
		String sqlDrop = "DROP TABLE " + tableName;
		executeSQL(sqlDrop);
	}
	
	/**
	 * 清除表中所有数据
	 * @param tableName	表名
	 */
	public void clearTableData(String tableName){
		if(TextUtils.isEmpty(tableName)){
			throw new IllegalArgumentException("please check you param,param is null or empty");
		}
		
		String sqlDrop = "DELETE FROM " + tableName;
		executeSQL(sqlDrop);
	}
	
	
	
	
	/**
	 * 执行SQL语句
	 * @param sql			SQL语句
	 */
	private void executeSQL(String sql){
		if(TextUtils.isEmpty(sql)){
			throw new IllegalArgumentException("please check your param,param is null or empty");
		}
		
		try{
			mSqLiteDatabaseWritable.execSQL(sql);
		}catch(SQLException sqlException){
			LogUtil.e(TAG, sql + "\n" + "is not illegal.");
			throw new IllegalArgumentException("please check your SQL:" + sql);
		}
	}
	
	/**
	 * 将Cursor转换成对应的结果集
	 * @param cursor	游标
	 * @param clazz		Class文件
	 * @return	返回集合
	 */
	private static<T> List<T> cursorToList(Cursor cursor,Class<T> clazz){
		if(cursor == null)return null;
		List<T> list = new ArrayList<T>();
		Field[] fields = clazz.getDeclaredFields();
		T t;
		try {
			while(cursor.moveToNext()){
				//每一行为一个实体，新建一个实体
				t = clazz.newInstance();
				String[] columnNames = cursor.getColumnNames();
				for(String columnName : columnNames){
					String fieldValue = cursor.getString(cursor.getColumnIndex(columnName));
					for(Field field : fields){
						if(ReflectUtil.isFiledWithName(field, columnName)){
							ReflectUtil.setFieldValue(t, field, columnName, fieldValue);
							break;
						}
					}
				}
				list.add(t);
			}
			return list;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return null;
	}
}
