package com.learngodplan.module.mood;

import com.learngodplan.webservice.LogResQueryRunnable;

import android.util.Log;
import android.widget.Toast;

public class LogRegController {
	public static String name;
	public static String pass;
	// 0 log , 1 reg 
	public static int regFlag;
	
	 public static void getUserInfo(){
		 name = LogRegActivity.nameText.getText().toString();
		 pass = LogRegActivity.passText.getText().toString();
		 Log.d("test get in controller", name + pass );
	 }
	 
	 //检查用户信息,不能为空，用户名不能只有空格，“&”,密码不能有"&"
	 public static boolean checkInfo(){
		 if(name.trim().isEmpty() || pass.trim().isEmpty()){
			 Toast.makeText(LogRegActivity.logRegContext, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
			 return false;
		 }
		 else{
			 if(name.contains("&") ||pass.contains("&")){
				 Toast.makeText(LogRegActivity.logRegContext, "用户名或密码不能包含'&'", Toast.LENGTH_SHORT).show();
				 return false;
			 }
			 else{
				 return true;
			 }
		 }
	 }
	 
	 public static void  log(){
		 getUserInfo();
		 regFlag = 0;
		 if(checkInfo()){
			 new Thread(new LogResQueryRunnable(name, pass, 0)).start();
		 }
	 }
	 
	 public static void  reg(){
		 getUserInfo();
		 regFlag = 1;
		 if(checkInfo()){
			 new Thread(new LogResQueryRunnable(name, pass, 1)).start();
		 }
	 }
}
