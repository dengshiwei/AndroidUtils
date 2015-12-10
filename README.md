##Android开发常用工具类
###1、AppUtil
该类包含常用的App工具类，涉及到版本号的获取、版本号的名称、应用程序图标等功能。

* public static String getPacketName(Context context)，获取包名
* public static String getVersionName(Context context)，获取VersionName(版本名称)
* public static int getVersionCode(Context context)，获取VersionCode(版本号)
* public static List<PackageInfo> getInstalledPackages(Context context)，获取所有安装的应用程序,不包含系统应用
* public static Drawable getApplicationIcon(Context context)，获取应用程序的icon图标
* public static void installApk(Activity activity,String path)，启动安装应用程序

###2、BitmapUtil
该类包含Bitmap常用的工具类，涉及到Bitmap的获取、Bitmap与Drawable的相互转换。

* public static Bitmap getBitmapFromResource(Context context,int id,int height,int width)，根据资源id获取指定大小的Bitmap对象
* public static Bitmap getBitmapFromFile(String path, int height, int width)，根据文件路径获取指定大小的Bitmap对象
* public static Bitmap getThumbnailsBitmap(Bitmap bitmap, int height, int width)，获取指定大小的Bitmap对象
* public static Drawable bitmapToDrawable(Context context, Bitmap bitmap)，将Bitmap对象转换成Drawable对象
* public static Bitmap drawableToBitmap(Drawable drawable)，将Drawable对象转换成Bitmap对象
* public static byte[] bitmapToByte(Bitmap bitmap)，将Bitmap对象转换为byte[]数组

###3、DateUtil
该类包含日期的常用处理，涉及到日期字符串与Date对象的各种格式相互转换、获取星期、年月份、天数等功能。

* public static String getNowDate(DatePattern pattern)，返回当前时间，格式2015-12-3	10:54:21
* public static Date stringToDate(String dateString, DatePattern pattern)，将一个日期字符串转换成Data对象
* public static String dateToString(Date date, DatePattern pattern)，将date转换成字符串
* public static String getWeekOfDate(Date date)，返回值为： "周日", "周一", "周二", "周三", "周四", "周五", "周六" 
* public static int getIndexWeekOfDate(Date date)，获取指定日期对应周几的序列（周一：1	周二：2	周三：3	周四：4	周五：5	周六：6	周日：7）
* public static int getNowMonth()，返回当前月份
* public static int getNowDay()，获取当前月号
* public static int getNowYear()，获取当前年份
* public static int getNowDaysOfMonth()，获取本月份的天数
* public static int daysOfMonth(int year,int month)，获取指定月份的天数

补充内部类DatePattern：为我所定义的日期格式的枚举类。

###4、JsonUtil
常用的Json工具类，包含Json转换成实体、实体转json字符串、list集合转换成json、数组转换成json

* public static <T> String objectToJson(T t)，将一个对象转换成一个Json字符串
* public static<T> T jsonToObject(String jsonString, Class<T> clazz)，将Json字符串转换成对应对象
* public static<T> String listToJson(List<T> list)，将List集合转换为json字符串
* public static<T> String arrayToJson(T[] array)，将数组转换成json字符串
* public static<T> T getJsonObjectValue(String json, String key, Class<T> clazz)，获取json字符串中的值
* public static<T> T getJsonObjectValue(JSONObject jsonObject, String key, Class<T> clazz)，获取jsonObject对象中的值
* public static ContentValues jsonToContentValues(String json)，json字符串转换为ContentValues


###5、LogUtil
Log日志工具类

* public static void i(String tag,String msg)，打印information日志
* public static void i(String tag, String msg, Throwable throwable)，打印information日志
* public static void v(String tag, String msg)，打印verbose日志
* public static void v(String tag, String msg, Throwable throwable)，打印verbose日志
* public static void d(String tag, String msg)，打印debug信息
* public static void d(String tag, String msg, Throwable throwable)，打印debug信息
* public static void w(String tag, String msg)，打印warn日志
* public static void w(String tag, String msg, Throwable throwable)，打印warn日志
* public static void e(String tag, String msg)，打印error日志
* public static void e(String tag, String msg, Throwable throwable)，throwable

###6、MeasureUtil
常用的测量工具类

* public static int getMeasuredHeight(View view)，获取控件的测量高度
* public static int getHeight(View view)，控件的高度
* public static int getMeasuredWidth(View view)，获取控件的测量宽度
* public static int getWidth(View view)，获取控件的宽度
* public static void setHeight(View view, int height) ，设置高度
* public static void setWidth(View view, int width)，设置View的宽度
* public static void setListHeight(ListView listView)，设置ListView的实际高度
* public static void setGridViewHeight(Context context, GridView gv, int n, int m)，设置GridView的高度

###7、NetWorkUtil
网络工具类，包含网络的判断、跳转到设置页面

* public static boolean isNetWorkEnable(Context context)，判断当前是否有网络连接
* public static boolean isWiFiConnected(Context context)，判断当前网络是否为wifi
* public static boolean isMobileDataEnable(Context context)，判断MOBILE网络是否可用
* public static boolean isWifiDataEnable(Context context)，connectivityManager
* public static void GoSetting(Activity activity)，跳转到网络设置页面


###8、PreferencesUtil
SharedPreferences工具类，包含常用的数值获取和存储

* public static boolean containsKey(Context context, String key)，是否包含key
* public static String getString(Context context, String key, String defValue)，获取String
* public static Set<String> getStringSet(Context context, String key,  Set<String> defValues)，获取Set<String> 集合
* public static int getInt(Context context, String key, int defValue)，获取int值
* public static float getFloat(Context context, String key, float defValue)，获取float值
* public static long getLong(Context context, String key, long defValue)，获取Long类型值
* public static boolean getBoolean(Context context, String key, boolean defValue)，获取boolean类型的值
* public static boolean putString(Context context, String key, String value)，保存Stirng类型的值
* public static boolean putStringSet(Context context, String key, Set<String> value)，保存Set<String>集合的值
* public static boolean putInt(Context context, String key, int value)， 保存int类型的值
* public static boolean putLong(Context context, String key, long value)，保存long类型的值
* public static boolean putFloat(Context context, String key, float value)，保存float类型的值
* public static boolean putBoolean(Context context, String key, boolean value)，保存boolean类型的值
* public static boolean removeKey(Context context, String key)，删除关键字key
* public static boolean clearValues(Context context)，清除所有的关键字

###9、ReflectUtil
反射工具类

* public static<T> void setFieldValue(T t,Field field, String fieldName, String value)，设置字段值
* public static Field getField(Class<?> clazz, String filedName)，根据字段名称获取指定Field字段
* public static Field getFieldByName(Field[] fields, String fieldName)，根据字段名称获取指定的Field
*  public static boolean isFiledWithName(Field field, String fieldName)，判断该字段是否为FieldName对应字段


###10、ScreenUtil
屏幕工具类，涉及到屏幕宽度、高度、密度比、(像素、dp、sp)之间的转换等。

* public static int getScreenWidth(Context context)、获取屏幕宽度，单位为px
* public static int getScreenHeight(Context context)，获取屏幕高度，单位为px
* public static float getDensity(Context context)，获取系统dp尺寸密度值
* public static float getScaledDensity(Context context)，获取系统字体sp密度值
* public static int dp2px(Context context, int dpValue)，dip转换为px大小
* public static int px2dp(Context context, int pxValue)，px转换为dp值
* public static int sp2px(Context context, int spValue)，sp转换为px
* public static int px2sp(Context context, int pxValue)，px转换为sp
* public static int getStatusHeight(Context context)，获得状态栏的高度 
* public static Bitmap snapShotWithStatusBar(Activity activity)，获取当前屏幕截图，包含状态栏 
* public static Bitmap snapShotWithoutStatusBar(Activity activity)，获取当前屏幕截图，不包含状态栏 
* public static DisplayMetrics getDisplayMetrics(Context context)，获取DisplayMetrics对象


###11、SDCardUtil
SD卡工具类，包含SD卡状态、路径、容量大小

* public static boolean isSDCardEnable()，判断SD卡是否可用
* public static String getSDCradPath()，获取SD卡路径
* public static File getSDCardFile()，获取SD卡路径
* public static String getSDCardDownloadCachePath()，获取SD卡DownloadCache路径
* public static File getSDCardDownloadCacheFile()，获取SD卡DownloadCache路径
* public static String getSDCardRootPath()，获取系统存储路径
* public static File getSDCardRootFile()，获取系统存储路径
* public static String getDataFilePath(Context context)，获取应用程序的/data/data目录
* public static String getDataCachePath(Context context)，/data/data/PackageName/cache的路径
* public static long getSDCardSize() ，获取SD卡大小
* public static long getSDCardAvailableSize()，获取SD卡大小
* public long getRomTotalSize()，获得手机内存总大小 
* public long getRomAvailableSize()，获得手机可用内存 

###12、ToastUtil
Toast工具类

* public static void showLongToast(Context context, String msg)，长时Toast
* public static void showShortToast(Context context, String msg)，短时Toast

###13、XMLUtil
XML文件工具类，包含：将xml文件解析成实体集合、获取xml标签值、将标签值解析成实体集合。这个工具类主要是讲XML的解析进行抽象出来，方便使用。

* public static<T> List<T> xmlToObject(String xml, Class<T> clazz, String tagEntity)，XML文件解析成实体,不涉及到标签的属性值。
* public static<T> List<T> attributeToObject(String xml, Class<T> clazz, String tagName)，获取xml字符串标签中的属性值
* public static String getTagAttribute(String xml, String tagName, String attributeName)，获取Xml文件中的属性值


###14、DataBaseHelper
数据库工具类，包含数据库的创建、表的创建、增删改查。

* public void createTables(List<Class<?>> tableClassList)，创建数据表,该套逻辑是基于标注解和字段注解进行判断
* public long insert(String tableName, ContentValues contentValues)，向数据库插入数据
* public void insertBySQL(String sqlInsert)，通过SQL语句进行插入数据
* public int update(String tableName, ContentValues contentValues,
String whereClause, String [] whereArgs)，更新数据信息
* public void updateBySQL(String sqlUpdate)，通过SQL语句进行数据库的更新
* public int delete(String tableName, String whereClause, String [] whereArgs)，删除数据
* public void deleteBySQL(String sqlUpdate)，通过SQL语句进行数据的删除
* public Cursor queryCursor(String sql, String [] whereArgs)，查询
* public <T> List<T> queryList(String sql, String[] whereArgs, Class<T> clazz )，查询数据库，返回集合。
* public <T> T queryFirst(String sql, String[] whereArgs, Class<T> clazz)，查询第一个记录
* public void beginTranslaction()，开启事务
* public void setTransactionSuccessful()，设置事务处理成功
* public void endTransaction()，设置事务处理结束
* public int getCountOfTable(String tableName)，获取某个表中的记录条数
* public int getCount(String sql,String[] whereArgs)，根据SQL语句，获取查询结果的条数
* public void dropTable(String tableName)，删除表，从数据库中删除该表
* public void clearTableData(String tableName)，清除表中所有数据


##操作案例：
![test](http://img.blog.csdn.net/20151210172755987)



