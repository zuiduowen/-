package com.learngodplan.module.mood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class LogRegHandler extends Handler {
	public String TAG = "debug in LogRegHandler";
    @Override
    public void handleMessage(Message msg){
    	  Log.d(TAG, "msg received");
    	  //0 log, 1 reg
    	  if(LogRegController.regFlag == 0){
        	  if(msg.what ==LogRegActivity.SUCCESS_CODE){
	     		      Log.d(TAG, "receive LOG_SUCCESS");
	     		      
	     		      Toast.makeText(LogRegActivity.logRegContext, "登陆成功", Toast.LENGTH_SHORT).show();
	 				  //登陆成功，保存用户信息, 跳转到心情墙
	 				  SharedPreferences sharedPreferences = LogRegActivity.logRegContext.getSharedPreferences("userInfo", Context.MODE_APPEND);  
	 				  Editor editor = sharedPreferences.edit();//获取编辑器  
	 				  editor.putBoolean("hasLog",	true);
	 				  if(LogRegActivity.name != null){
	 				     Log.d("check LogRegActivity.name", LogRegActivity.name);
	 				  }
	 				  else
	 				  {
	 					 Log.d("check LogRegActivity.name","null");
	 				  }
	 				  editor.putString("name", LogRegController.name);  
	 				  editor.putString("password", LogRegController.pass);  
	 				  editor.commit();//提交修改  
	 				  
	 				  Intent intent = new Intent();
	 				  LogRegActivity.acInstance.setResult(Activity.RESULT_OK, intent);
	 				  LogRegActivity.acInstance.finish();
	 				  return;
     	       }
	     	   else{
	     		   Log.d(TAG, "log  fail");
	     		   Toast.makeText(LogRegActivity.logRegContext, "登陆失败 : "+msg.obj.toString(), Toast.LENGTH_SHORT).show();
	     	   }
    	  }
    	  else{
	    	  if(msg.what == LogRegActivity.SUCCESS_CODE){
	    		   Log.d(TAG, "receive REG_SUCCESS");
	    		   Toast.makeText(LogRegActivity.logRegContext, "注册成功", Toast.LENGTH_SHORT).show();
	    			 //注册成功,保存用户信息,跳转到心情墙
		    		  SharedPreferences sharedPreferences = LogRegActivity.logRegContext.getSharedPreferences("userInfo", Context.MODE_APPEND);  
		    		  Editor editor = sharedPreferences.edit();//获取编辑器  
		    		  editor.putBoolean("hasLog",	true);
		    		  editor.putString("name", LogRegActivity.name);  
		    		  editor.putString("password", LogRegActivity.pass);  
		    		  editor.commit();//提交修改  
		              Intent intent = new Intent();
		              // Set result and finish this Activity
		              LogRegActivity.acInstance.setResult(Activity.RESULT_OK, intent);
		              LogRegActivity.acInstance.finish();
	    			 
	    			  return;
	    	  }
	    	  else{
	    		   Toast.makeText(LogRegActivity.logRegContext, "注册失败： "+msg.obj.toString(), Toast.LENGTH_SHORT).show();
	    		   Log.d(TAG, "register fail");
	    	  }
    	  }
    }
}
