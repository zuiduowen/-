package com.learngodplan.webservice;

import android.os.Handler;
import android.util.Log;
import cn.bmob.v3.listener.SaveListener;

import com.learngodplan.domain.serverside.Mood;
import com.learngodplan.module.mood.AddMoodActivity;

public class AddMoodRunnable implements Runnable {
    public static Mood myMood = new Mood();
	
	public static Handler mHandler;
	
	public AddMoodRunnable(String n,  String m){
		myMood.setMoodAuthor(n);
		myMood.setMoodContent(m);
		
		Log.d("test addmood runnable", myMood.getMoodAuthor() + ' ' +myMood.getMoodContent());
	}
	
	@Override
	public void run() {	
		try{			
			myMood.save(AddMoodActivity.ct , new SaveListener() {
			    @Override
			    public void onSuccess() {
			    	AddMoodActivity.moodText.setText("");
			    	AddMoodActivity.mHandler.obtainMessage(AddMoodActivity.SUCCESS_CODE, -1, -1, 1).sendToTarget();
			    }

			    @Override
			    public void onFailure(int code, String arg0) {
			    	AddMoodActivity.mHandler.obtainMessage(AddMoodActivity.FAIL_CODE, -1, -1, arg0).sendToTarget();
			    }
			});            
		}catch(Exception e){	
			AddMoodActivity.mHandler.obtainMessage(AddMoodActivity.FAIL_CODE, -1, -1, 1).sendToTarget();
		}
		 
	}

}
