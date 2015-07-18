package com.learngodplan.webservice;

import android.util.Log;
import cn.bmob.v3.listener.SaveListener;

import com.learngodplan.domain.serverside.MyUser;
import com.learngodplan.module.mood.LogRegActivity;

public class LogResQueryRunnable implements Runnable {
	    public static MyUser bu =  new MyUser();
	    public static String pass;
	    public static String name;
	    
	    //0 is log, 1 is reg
	    public static int logOrReg;
	    
	    public LogResQueryRunnable(String n, String p, int flag){
	    	Log.d("test in queryRunnable 1", LogResQueryRunnable.name+LogResQueryRunnable.pass );
	    	LogResQueryRunnable.name = n;
	    	LogResQueryRunnable.pass = p;
	    	logOrReg = flag;
	    	Log.d("test in queryRunnable 2", LogResQueryRunnable.name+LogResQueryRunnable.pass );
	    }
	    
        @Override
        public void run(){
        	   MyUser bu = new MyUser();
        	   bu.setUsername(LogResQueryRunnable.name);
        	   bu.setPassword(LogResQueryRunnable.pass);
               switch(logOrReg){
                   //注册
                   case 1:{ 
                          bu.signUp(LogRegActivity.logRegContext, new SaveListener(){
                  	          @Override
                  	          public void onSuccess(){
                  	    	       Log.d("debug", "runnable sign up success");
                  	    	       LogRegActivity.mHandler.obtainMessage(LogRegActivity.SUCCESS_CODE, -1, -1, 1).sendToTarget();
                  	          }
                  	     
                  	          public void onFailure(int code, String msg){
                  	    	       Log.d("debug", "runnable sign up fail");
                  	    	     LogRegActivity.mHandler.obtainMessage(LogRegActivity.FAIL, -1, -1, msg).sendToTarget();
                  	          }
                         });	   
                   };break;
                   
                   case 0:{
                	   bu.login(LogRegActivity.logRegContext, new SaveListener() {
                		    @Override
                		    public void onSuccess() {
                		    	LogRegActivity.mHandler.obtainMessage(LogRegActivity.SUCCESS_CODE, -1, -1, 1).sendToTarget();
                		    }
                		    @Override
                		    public void onFailure(int code, String msg) {
                		    	LogRegActivity.mHandler.obtainMessage(LogRegActivity.FAIL, -1, -1, msg).sendToTarget();
                		    }
                		});break;
                   }
               }
        }
}
