package com.learngodplan.module.plan;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.learngodplan.R;
import com.learngodplan.db.FoodDBO;
import com.learngodplan.db.TaskDBO;
import com.learngodplan.domain.local.Food;
import com.learngodplan.domain.local.Task;
import com.learngodplan.module.home.MainActivity;
import com.learngodplan.module.monitor.CompulsoryService;
import com.learngodplan.module.monitor.VibratorTool;

@SuppressLint("InflateParams")
public class InstantTaskController {
	//0为当前显示的是start，1为当前显示的是pause
    public static int buttonPressFlag = 0;
    public static int planTime;
    public static boolean timeSelectFlag = false;
    
	//判断service是否已经开启的flag，防止重复开启
	public static boolean serviceFlag;
	//监听用户是否有打开其他应用的服务
	
	//计时变量
    public static int miss = 0;
	//按下暂停按钮时候的miss值
    public static int pauseMiss = 0;
    //当前执行的task
    public static Task currentTask;
    public static TaskDBO tDBO;
    
    public static FoodDBO foodDBO;
    public static ArrayList<Food> allFood;
	
	
	public static void toPause(){
    	if(buttonPressFlag == 1){
        	Log.d("test onPause", "test onPause");
        	if(!serviceFlag){
        		Log.d("d", "start service");
        		InstantTaskActivity.ct.startService(new Intent(InstantTaskActivity.ct, CompulsoryService.class));
            	serviceFlag = true;
        	}
    	}
	}
	
	public static void toDestroy(){
    	if(serviceFlag){
        	Intent stopIntent = new Intent(InstantTaskActivity.ct, CompulsoryService.class);
        	InstantTaskActivity.ct.stopService(stopIntent);
        	serviceFlag = false;
        	Log.d("d", "onDestroy complete");
    	}
	}
	
    public static void getTaskDataFromIntent(){
        currentTask = new Task();
        Intent it = InstantTaskActivity.itan.getIntent();
        Bundle bd = it.getBundleExtra("taskBundle");
        
        currentTask.tId = bd.getInt("taskId");
        currentTask.tName = bd.getString("taskName");
        currentTask.tPriority = bd.getInt("taskPriority");
        currentTask.tPlanTime = bd.getInt("taskPlanTime");
        currentTask.tTotalTime = bd.getInt("taskToalTime");
        currentTask.isFinished = bd.getInt("isFInished");
        currentTask.tStartTime = bd.getString("taskStartTime");
        currentTask.tEndTime = bd.getString("taskEndTime");
    }

    public static void clickOnBottomButton(){
		if(buttonPressFlag == 0){
			if(timeSelectFlag){
					//当前按下的是start，开始计时
					buttonPressFlag = 1;
					InstantTaskActivity.bottomBtIv.setImageResource(R.drawable.shortplan_bt_pause);		
					if(pauseMiss == 0){}
					else{
						InstantTaskActivity.ch.setText(formatMiss(pauseMiss + 1));
						Log.d("test setText", formatMiss(pauseMiss + 1));
					}
					new Handler().postDelayed(new Runnable(){
						@Override
						public void run() {
							InstantTaskActivity.ch.start();
						}
					}, 1000);
			}
			else{
				Toast.makeText(InstantTaskActivity.ct, "请在屏幕上方选择时长", Toast.LENGTH_SHORT).show();
			}
		}
		else{
			//当前按下的是pause，暂停计时器
			buttonPressFlag = 0;
			pauseMiss = miss;
			Log.d("test", formatMiss(pauseMiss));
			InstantTaskActivity.bottomBtIv.setImageResource(R.drawable.shortplan_bt_start);
			String tempText = (String) InstantTaskActivity.ch.getText();
			InstantTaskActivity.ch.setBase(convertStrTimeToLong(InstantTaskActivity.ch.getText().toString()));
			InstantTaskActivity.ch.stop();
			InstantTaskActivity.ch.setText(tempText);
		}
    }
    
    public static void clickOnHalf(){
		resetButton();
		planTime = 3;
		timeSelectFlag = true;
		InstantTaskActivity.halfView.setImageResource(R.drawable.shortplan_press_bt_half);
    }
    
    public static void clickOnone(){
		resetButton();
		planTime = 3600;
		timeSelectFlag = true;
		InstantTaskActivity.oneView.setImageResource(R.drawable.shortplan_press_bt_one);
    }
    
    public static void clickOnTwo(){
		resetButton();
		planTime = 5400;
		timeSelectFlag = true;
		InstantTaskActivity.twoView.setImageResource(R.drawable.shortplan_press_bt_two);
    }
    
    public static void clickOnThree(){
		resetButton();
		planTime = 7200;
		timeSelectFlag = true;
		InstantTaskActivity.threeView.setImageResource(R.drawable.shortplan_press_bt_three);
    }
    
    public static void clickOnChest(LayoutInflater popInflater){
		View popView = 	popInflater.inflate(R.layout.petfood_popwindow, null);
		ImageView foodView = (ImageView)popView.findViewById(R.id.popFoodView);
		TextView foodText = (TextView)popView.findViewById(R.id.popFoodText);
		
		int foodType = (int)(Math.random()*4);
		//随机获得奖励，并更新数据库
		switch (foodType){
	        case 0:{ 
	        	foodView.setImageResource(R.drawable.food1);
	        	foodText.setText("+20饥饿值");
	        	allFood.get(0).foodNum += 1;
	        	foodDBO.update(allFood.get(0));
	        }break;
		    case 1:{ 
		    	foodView.setImageResource(R.drawable.food2);
		    	foodText.setText("+25 饥饿值");
	        	allFood.get(1).foodNum += 1;
	        	foodDBO.update(allFood.get(1));
		    	}break;
		    case 2: {
		    	foodView.setImageResource(R.drawable.food2);
		    	foodText.setText("+30 饥饿值");
	        	allFood.get(2).foodNum += 1;
	        	foodDBO.update(allFood.get(2));
	        	}break;
		    case 3: {
		    	foodView.setImageResource(R.drawable.food4);
		    	foodText.setText("+35 饥饿值");
	        	allFood.get(3).foodNum += 1;
	        	foodDBO.update(allFood.get(3));
		    	}break;
		    case 4: {
		    	foodView.setImageResource(R.drawable.food5);
		    	foodText.setText("+40 饥饿值");
	        	allFood.get(4).foodNum += 1;
	        	foodDBO.update(allFood.get(4));
		    	}break;
		}
		
		AlertDialog.Builder awardWindow = new AlertDialog.Builder(InstantTaskActivity.ct);
		awardWindow.setTitle("获得饲料奖励");
		awardWindow.setView(popView);
		awardWindow.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//确认获得的全部奖励后,返回首页
				InstantTaskActivity.chestView.setVisibility(View.GONE);
				InstantTaskActivity.bottomBtIv.setVisibility(View.VISIBLE);
				resetButton();
				//test
				planTime = 3600 * 3;
				currentTask.tTotalTime += planTime / 3600;
				//判断是否已近完成了任务,如果完成，则更新Task的isFinished否则的话只更新Task的TotalTime
				if(currentTask.tPlanTime <= currentTask.tTotalTime){
					currentTask.isFinished = 1;
				}
				tDBO.update(currentTask);
				
				Intent it = new Intent(InstantTaskActivity.ct, MainActivity.class);
				InstantTaskActivity.ct.startActivity(it);
			}
		});
		awardWindow.show();
    }
    
	//清空全部计时数据，重置按钮
	public static void resetButton(){
		InstantTaskActivity.halfView.setImageResource(R.drawable.shortplan_bt_halfhour);
		InstantTaskActivity.oneView.setImageResource(R.drawable.shortplan_bt_onehour);
		InstantTaskActivity.twoView.setImageResource(R.drawable.shortplan_bt_twohour);
		InstantTaskActivity.threeView.setImageResource(R.drawable.shortplan_bt_threehour);
		InstantTaskActivity.bottomBtIv.setImageResource(R.drawable.shortplan_bt_start);
		InstantTaskActivity.ch.setText("00:00:00");
		InstantTaskActivity.ch.stop();
		miss = 0;
		planTime = 0;
		buttonPressFlag = 0;
		timeSelectFlag = false;
	}
    
   
	public static void ticker(Chronometer ch){
		Log.d("log miss", String.valueOf(miss));
		
		//如果计时到了，就播放宝箱动画,隐藏底部按钮
		if(miss  >= planTime){
			Toast.makeText(InstantTaskActivity.ct,"over"+String.valueOf(planTime), Toast.LENGTH_SHORT).show();
			ch.stop();
		    ch.setText(formatMiss(miss));
		    InstantTaskActivity.bottomBtIv.setVisibility(View.INVISIBLE);
		    InstantTaskActivity.chestView.setBackgroundResource(R.layout.chest_animation);
		    InstantTaskActivity.chestView.setVisibility(View.VISIBLE);
			AnimationDrawable anim = (AnimationDrawable)InstantTaskActivity.chestView.getBackground();
			anim.start();
			long[] vibrateArray = {500, 200, 500, 200, 500, 200, 500, 200, 500, 200};
			VibratorTool.Vibrate(InstantTaskActivity.itan, vibrateArray, false);
			return;
		}
		miss++;
	    ch.setText(formatMiss(miss));
	}
	
	/**
	 * 将String类型的时间转换成long,如：12:01:08
	 * @param strTime String类型的时间
	 * @return long类型的时间
	 * */
	protected static long convertStrTimeToLong(String strTime) {
	    String []timeArry=strTime.split(":");
	    long longTime= 0;
	    if (timeArry.length==2) {//如果时间是MM:SS格式
	        longTime=Integer.parseInt(timeArry[0])*1000*60+Integer.parseInt(timeArry[1])*1000;
	    }else if (timeArry.length==3){//如果时间是HH:MM:SS格式
	        longTime=Integer.parseInt(timeArry[0])*1000*60*60+Integer.parseInt(timeArry[1])
	              *1000*60+Integer.parseInt(timeArry[2])*1000 ;
	    }            
	    return SystemClock.elapsedRealtime()-longTime;
	}
	
	public static String formatMiss(int miss){     
        String hh=miss/3600>9?miss/3600+"":"0"+miss/3600;
        String mm=(miss % 3600)/60>9?(miss % 3600)/60+"":"0"+(miss % 3600)/60;
        String ss=(miss % 3600) % 60>9?(miss % 3600) % 60+"":"0"+(miss % 3600) % 60;
        return hh+":"+mm+":"+ss;      
    } 	
}
