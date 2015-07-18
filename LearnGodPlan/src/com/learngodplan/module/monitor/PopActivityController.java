package com.learngodplan.module.monitor;

import android.content.Intent;

import com.learngodplan.module.plan.InstantTaskController;

public class PopActivityController {
	
	public static void imWrong(){
    	PopActivity.popInstance.finish();
    	//跳转到桌面
    	Intent intent=new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	PopActivity.ct.startActivity(intent);
	}
	
	public static void toDestop(){
		PopActivity.popInstance.finish();
    	//跳转到桌面
    	Intent intent=new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	PopActivity.ct.startActivity(intent);
	}
	
	public static void exitLearn(){
    	Intent stopServiceIntent = new Intent(PopActivity.ct, CompulsoryService.class);
    	PopActivity.ct.stopService(stopServiceIntent);
    	
    	InstantTaskController.serviceFlag = false;
		PopActivity.popInstance.finish();
		
    	//跳转到桌面
    	Intent intent=new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	PopActivity.ct.startActivity(intent);
	}
}
