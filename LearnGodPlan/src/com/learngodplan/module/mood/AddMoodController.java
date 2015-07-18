package com.learngodplan.module.mood;

import java.util.Calendar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.learngodplan.webservice.AddMoodRunnable;

public class AddMoodController extends Handler {
	private static String name;
	private static String date;
	private static String mood;

    @Override
    public void handleMessage(Message msg){
    	if(msg.what == AddMoodActivity.SUCCESS_CODE){
    		Toast.makeText(AddMoodActivity.ct, "发送成功", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	else{
    		Toast.makeText(AddMoodActivity.ct, "发送失败"+msg.obj.toString(), Toast.LENGTH_SHORT).show();
    	    return;
    	}
    }
    
    
    //初始化要发送的数据
    public static void initDataSent(){
    	mood = AddMoodActivity.moodText.getText().toString();
    	//读取保存的用户信息
        SharedPreferences preferences = AddMoodActivity.ct.getSharedPreferences("userInfo",   Activity.MODE_PRIVATE);  
        name = preferences.getString("name", ""); 
        //获取当前日期
    	Calendar ca = Calendar.getInstance();
    	int  year = ca.get(Calendar.YEAR);
    	int month = ca.get(Calendar.MONTH) + 1;
    	int day = ca.get(Calendar.DAY_OF_MONTH);
    	int hour = ca.get(Calendar.HOUR_OF_DAY);
    	int minute = ca.get(Calendar.MINUTE);
        date = year + "-" + month + "-" + day + "-" + hour + ":" + minute;
        
        Log.d("data after init", name+' '+mood+' '+date);
    }
    
    public static void addMood(){
    	initDataSent();
    	//检查心情是否为空，包含&
    	if(mood.trim().isEmpty() || mood.contains("&")){
    		Toast.makeText(AddMoodActivity.ct, "心情内容不能为空或包含'&' ", Toast.LENGTH_SHORT).show();
    	    return;
    	}
    	else{
    		//发送心情
    		Thread thread = new Thread(new AddMoodRunnable(name, mood));
    		thread.start();
    	}
    }

}
