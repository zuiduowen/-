package com.learngodplan.module.mood;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import cn.bmob.v3.Bmob;

import com.example.learngodplan.R;

public class LogRegActivity extends Activity {
	public static EditText nameText;
	public static EditText passText;
	public static String name;
	public static String pass;
	
    public static int SUCCESS_CODE = 1;
    public static int FAIL = 2;
    
	public static Context logRegContext;
	public static LogRegActivity acInstance = null;
	public static Handler mHandler;
	
    public static String TAG = "debug";
	
	 protected void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.login_or_register);  
	  
	        //初始化视图成员
	        nameText = (EditText)findViewById(R.id.username);
	        passText = (EditText)findViewById(R.id.password);
	        
	        logRegContext = this;
	        acInstance = this;
	        //初始化Bmob云服务
	        Bmob.initialize(this,  "65c2d08a414485b7da0e15c33f88185e");
	        
			mHandler = new LogRegHandler();
	 }
	 
	 public void onLogClick(View v){
		 LogRegController.log();
	 }
	 
	 public void onRegClick(View v){
		 LogRegController.reg();
	 }
}