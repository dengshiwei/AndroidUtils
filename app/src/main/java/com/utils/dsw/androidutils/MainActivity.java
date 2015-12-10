package com.utils.dsw.androidutils;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dsw.androidutils.AppUtil;
import com.dsw.androidutils.BitmapUtil;
import com.dsw.androidutils.DateUtil;
import com.dsw.androidutils.JsonUtil;
import com.dsw.androidutils.MeasureUtil;
import com.dsw.androidutils.NetWorkUtil;
import com.dsw.androidutils.SDCardUtil;
import com.dsw.androidutils.ScreenUtil;
import com.dsw.androidutils.ToastUtil;
import com.dsw.androidutils.XMLUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private MainActivity _this;
    private ListView listView;
    private MyAdapter adapter;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _this = this;
        listView = (ListView) findViewById(R.id.list_View);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        initDateAndListener();
    }

    private void initDateAndListener(){
        List<String> list = new ArrayList<String>();
        list.add("AppUtil实例"); list.add("BitmapUtil实例"); list.add("DateUtil实例"); list.add("JsonUtil实例");
        list.add("MeasureUtil实例"); list.add("NetWorkUtil实例"); list.add("PreferencesUtil实例"); list.add("ScreenUtil实例");
        list.add("SDCardUtil实例"); list.add("XMLUtil实例"); list.add("DataBaseHelper实例"); list.add("ReflectUtil实例");
        adapter = new MyAdapter(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doActionItemClick(adapter.getItem(position).toString());
            }
        });
    }

    class MyAdapter extends BaseAdapter{
        List<String> list;
        public MyAdapter(List<String> list){
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(_this);
            tv.setText(list.get(position));
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setTextSize(17);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setPadding(5,5,5,5);
            return tv;
        }
    }

    private void doActionItemClick(String name){
        linearLayout.removeAllViews();
        switch (name){
            case "AppUtil实例":
                TextView textView = new TextView(_this);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(AppUtil.getPacketName(_this) + "\n")
                        .append(AppUtil.getVersionCode(_this) + "\n")
                        .append(AppUtil.getVersionName(_this) + "\n");
                textView.setText(stringBuffer.toString());
                ImageView imageView = new ImageView(_this);
                imageView.setBackgroundDrawable(AppUtil.getApplicationIcon(_this));
                linearLayout.addView(textView);
                linearLayout.addView(imageView);
                break;
            case "BitmapUtil实例":
                ImageView image = new ImageView(_this);
                image.setLayoutParams(new ViewGroup.LayoutParams(200,200));
                MeasureUtil.setHeight(image,ScreenUtil.px2dp(_this, 100));
                MeasureUtil.setWidth(image, ScreenUtil.px2dp(_this, 100));
                image.setImageBitmap(BitmapUtil.getBitmapFromResource(_this, R.mipmap.src, ScreenUtil.px2dp(_this, 100), ScreenUtil.px2dp(_this, 100)));
                linearLayout.addView(image);
                break;
            case "DateUtil实例":
                TextView textViewDate = new TextView(_this);
                textViewDate.setText(DateUtil.getNowDate(DateUtil.DatePattern.ALL_TIME));
                linearLayout.addView(textViewDate);
                break;
            case "JsonUtil实例":
                Student student = new Student();
                student.setAge(21);
                student.setName("soul");
                TextView textViewJson = new TextView(_this);
                textViewJson.setText(JsonUtil.objectToJson(student));
                linearLayout.addView(textViewJson);
                break;
            case "MeasureUtil实例":
                break;
            case "NetWorkUtil实例":
                TextView textViewNet = new TextView(_this);
                textViewNet.setText(new StringBuilder().append("isMobileDataEnable:" + NetWorkUtil.isMobileDataEnable(_this) + "\n")
                        .append("isNetWorkEnable:" + NetWorkUtil.isNetWorkEnable(_this) + "\n")
                        .append("isWiFiConnected:" + NetWorkUtil.isWiFiConnected(_this) + "\n").toString());
                linearLayout.addView(textViewNet);
                break;
            case "PreferencesUtil实例":
                break;
            case "ScreenUtil实例":
                break;
            case "SDCardUtil实例":
                TextView textViewCard = new TextView(_this);
                textViewCard.setText(new StringBuilder().append("getDataCachePath:" + SDCardUtil.getDataCachePath(_this) + "\n")
                        .append("getDataFilePath:" + SDCardUtil.getDataFilePath(_this) + "\n")
                        .append("getSDCardDownloadCachePath:" + SDCardUtil.getSDCardDownloadCachePath() + "\n")
                        .append("getSDCardRootPath:" + SDCardUtil.getSDCardRootPath() + "\n")
                        .append("getSDCradPath:" + SDCardUtil.getSDCradPath() + "\n")
                        .append("getSDCardAvailableSize:" + SDCardUtil.getSDCardAvailableSize() + "\n")
                        .append("getSDCardSize:" + SDCardUtil.getSDCardSize() + "\n")
                        .append("isSDCardEnable:" + SDCardUtil.isSDCardEnable() + "\n").toString());
                linearLayout.addView(textViewCard);
                break;
            case "XMLUtil实例":
                String strRequest = "";
                strRequest = strRequest + "<?xml version='1.0' encoding='utf-8' standalone='yes'?>";
                strRequest = strRequest + "<student>";
                strRequest = strRequest + "<body name=\"abc\" age=\"12\">";
                strRequest = strRequest + "<stu><name>zhangsan</name><age>21</age></stu>";
                strRequest = strRequest + "<stu><name>lisi</name><age>52</age></stu>";
                strRequest = strRequest + "<stu><name>wangwu</name><age>22</age></stu>";
                strRequest = strRequest + "</body>";
                strRequest = strRequest + "</student>";
                TextView textViewSrcXml = new TextView(_this);
                textViewSrcXml.setText(strRequest);
                linearLayout.addView(textViewSrcXml);
                TextView tv_attribute = new TextView(_this);
                tv_attribute.setText("body的name值：" + XMLUtil.getTagAttribute(strRequest,"body","name"));
                linearLayout.addView(tv_attribute);
                break;
            case "DataBaseHelper实例":
                break;
            case "ReflectUtil实例":
                break;
        }
    }
}
