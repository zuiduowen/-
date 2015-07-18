package com.learngodplan.webservice;

import java.util.List;

import android.util.Log;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.learngodplan.domain.serverside.Mood;
import com.learngodplan.module.home.MainActivity;
import com.learngodplan.module.mood.AddMoodActivity;
import com.learngodplan.module.mood.FragmentMood;

public class LoadMoodRunnable implements Runnable {
	public static int SUCCESS_CODE = 1;
	public static int FAIL_CODE = 2;
	
	private int dataNum = 15;
	
	public LoadMoodRunnable(){}
	
	@Override
	public void run() {	
		try{
			BmobQuery<Mood> query = new BmobQuery<Mood>();
			query.setLimit(dataNum);
			query.order("createdAt");
			query.findObjects(MainActivity.mainAcContext, new FindListener<Mood>() {
			       @Override
			        public void onSuccess(List<Mood> object) {
			    	      if(object == null){
			    	    	  Log.e("see object","null");
			    	      }
			    	      //将查询结果封装在msg的obj上传递给LoadMoodController
			    	      FragmentMood.mHandler.obtainMessage(LoadMoodRunnable.SUCCESS_CODE, -1, -1, object).sendToTarget();
			        }
			        @Override
			        public void onError(int code, String msg) {
			            // TODO Auto-generated method stub
			        	FragmentMood.mHandler.obtainMessage(LoadMoodRunnable.FAIL_CODE, -1, -1, msg).sendToTarget();
			        }
			});
            
		 } catch(Exception e){
        	 Log.d("error","未知的错误");
         }
	}
	
//	//检查从服务器获取的数据,决定返回给handler的内容
//	public void checkMessageFromServer(String bf){
//		//加载成功
//		if(!bf.equals("LF")){
//			Message.obtain(FragmentMood.mHandler, LoadMoodRunnable.SUCCESS_CODE, -1, -1, moodStr).sendToTarget();
//		}
//		else{
//			Log.d("else", bf);
//			Message.obtain(FragmentMood.mHandler, LoadMoodRunnable.FAIL_CODE, -1, -1, null).sendToTarget();
//		}
//	}

}
