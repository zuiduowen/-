package com.learngodplan.module.plan;

import java.util.Calendar;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Toast;

import com.example.learngodplan.R;
import com.learngodplan.db.TaskDBO;
import com.learngodplan.domain.local.Task;
import com.learngodplan.module.home.MainActivity;

public class NewPlanController {
	//数据成员
	private static int myPriority = 0;
	private static TaskDBO taskDBO;
	private static String taskName;
	private static int taskTime;
	private static String taskStartTime;
	private static String taskEndTime;
	
	private static int startYear;
	private static int startMonth;
	private static int startDay;
	private static int endYear;
	private static int endMonth;
	private static int endDay;
	
	public static void  initDataMember(){
    	taskDBO = new TaskDBO(NewPlanActivity.ct);	
	
	   	// 获取当前的年月日
	   	Calendar ca = Calendar.getInstance();
	   	int  year = ca.get(Calendar.YEAR);
	   	final int month = ca.get(Calendar.MONTH);
	   	final int day = ca.get(Calendar.DAY_OF_MONTH);
	   	//默认开始时间和结束时间都是当前日期
			startYear = year;
			startMonth = month+1;
			startDay = day;
			endYear = year;
			endMonth = month+1;
			endDay = day;
			taskStartTime = String.valueOf(year) 
										+ "-" 
										+ String.valueOf(month+1) 
										+ "-" 
										+ String.valueOf(day);
			taskEndTime = String.valueOf(year) 
										+ "-" 
										+ String.valueOf(month+1) 
										+ "-" 
										+ String.valueOf(day);
			
			Toast.makeText(NewPlanActivity.ct, "当前的时间为"+taskStartTime, Toast.LENGTH_SHORT).show();
			
			NewPlanActivity.startDatePicker = (DatePicker)NewPlanActivity.itan.findViewById(R.id.startDatePicker);
			NewPlanActivity.startDatePicker.init(year, month, day, new OnDateChangedListener(){
					@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						taskStartTime = String.valueOf(year) 
								                  + "-" 
								                  + String.valueOf(monthOfYear+1) 
								                  + "-" 
								                  + String.valueOf(dayOfMonth);
						startYear = year;
						startMonth = monthOfYear+1;
						startDay = dayOfMonth;
					}
		   	});
		   	
			NewPlanActivity.endDatePicker = (DatePicker)NewPlanActivity.itan.findViewById(R.id.endDatePicker);
			NewPlanActivity.endDatePicker.init(year, month, day, new OnDateChangedListener(){
					@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						taskEndTime = String.valueOf(year) 
				                  + "-" 
				                  + String.valueOf(monthOfYear+1) 
				                  + "-" 
				                  + String.valueOf(dayOfMonth);
						endYear = year;
						endMonth = monthOfYear+1;
						endDay = dayOfMonth;		   
						
						NewPlanActivity.startDatePicker = (DatePicker)NewPlanActivity.itan.findViewById(R.id.startDatePicker);
						NewPlanActivity.startDatePicker.init(year, month, day, new OnDateChangedListener(){
							@Override
							public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
								taskStartTime = String.valueOf(year) 
										                  + "-" 
										                  + String.valueOf(monthOfYear+1) 
										                  + "-" 
										                  + String.valueOf(dayOfMonth);
								startYear = year;
								startMonth = monthOfYear+1;
								startDay = dayOfMonth;
							}
				   	});
				   	
					NewPlanActivity.endDatePicker = (DatePicker)NewPlanActivity.itan.findViewById(R.id.endDatePicker);
					NewPlanActivity.endDatePicker.init(year, month, day, new OnDateChangedListener(){
							@Override
							public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
								taskEndTime = String.valueOf(year) 
						                  + "-" 
						                  + String.valueOf(monthOfYear+1) 
						                  + "-" 
						                  + String.valueOf(dayOfMonth);
								endYear = year;
								endMonth = monthOfYear+1;
								endDay = dayOfMonth;
							}
				   	});
					}
		   	});
		}
	
	public static void clickOnStar(View v){
		resetAllStar();
        Log.e("d", "call here");
		switch(v.getId()){
			case R.id.priorityStar5: myPriority = 5; break;
			case R.id.priorityStar4: myPriority = 4; break;
			case R.id.priorityStar3: myPriority = 3; break;
			case R.id.priorityStar2: myPriority = 2; break;
			case R.id.priorityStar1: myPriority = 1; break;
        }
		Log.d("d", String.valueOf(myPriority));
		
		switch(v.getId()){
			case R.id.priorityStar5: NewPlanActivity.star5.setImageResource(R.drawable.newplan_star_select);
			case R.id.priorityStar4: NewPlanActivity.star4.setImageResource(R.drawable.newplan_star_select);
			case R.id.priorityStar3: NewPlanActivity.star3.setImageResource(R.drawable.newplan_star_select);
			case R.id.priorityStar2: NewPlanActivity.star2.setImageResource(R.drawable.newplan_star_select);
			case R.id.priorityStar1: NewPlanActivity.star1.setImageResource(R.drawable.newplan_star_select);
	     }
	}
	
    public static void resetAllStar(){
    	NewPlanActivity.star5.setImageResource(R.drawable.newplan_star);
    	NewPlanActivity.star4.setImageResource(R.drawable.newplan_star);
    	NewPlanActivity.star3.setImageResource(R.drawable.newplan_star);
    	NewPlanActivity.star2.setImageResource(R.drawable.newplan_star);
    	NewPlanActivity.star1.setImageResource(R.drawable.newplan_star);
    }
    
    public static void clickOnAdd(){
        if(NewPlanActivity.taskTimeEdit.getText().toString().length() == 0 || NewPlanActivity.taskTimeEdit.getText().toString().trim().isEmpty()){
    		Toast.makeText(NewPlanActivity.ct, "计划时长不能为空 或 0", Toast.LENGTH_SHORT).show();
    		return;
        }
        
		taskName = NewPlanActivity.taskNameEdit.getText().toString();
        taskTime =Integer.parseInt((NewPlanActivity.taskTimeEdit.getText().toString()));

    	if(myPriority == 0){
    		Toast.makeText(NewPlanActivity.ct, "请为计划选择优先级", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	if(taskName.trim().isEmpty()){
    		Toast.makeText(NewPlanActivity.ct, "计划名称不能为空", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	if(taskTime == 0){
    		Toast.makeText(NewPlanActivity.ct, "计划时长不能为空 或 0", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	if(startYear > endYear){
    		Toast.makeText(NewPlanActivity.ct, "起始日期不能晚于终止日期", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
		if(startYear == endYear && startMonth > endMonth){
    		Toast.makeText(NewPlanActivity.ct, "起始日期不能晚于终止日期", Toast.LENGTH_SHORT).show();
    		return;
		}
		
		if(startYear == endYear && startMonth == endMonth && startDay > endDay){
    		Toast.makeText(NewPlanActivity.ct, "起始日期不能晚于终止日期", Toast.LENGTH_SHORT).show();
    		return;
		}
		getDataAndInsert();

    }
    
    public static void getDataAndInsert(){
		Log.d("task Start Time", taskStartTime);
		Log.d("task end  Time", taskEndTime);
    	//debug
		Log.e("debug", taskName);
		
        Task temp  = new Task();
        
        temp.tName = taskName;
        temp.tPriority = myPriority;
        temp.isFinished = 0;
        temp.tPlanTime = taskTime;
        temp.tTotalTime = 0;
        temp.tStartTime = taskStartTime;
        temp.tEndTime = taskEndTime;

        taskDBO.insert(temp);
        Toast.makeText(NewPlanActivity.ct, "添加成功", Toast.LENGTH_SHORT).show();
        
        //添加成功后跳转到首页
        Intent it = new Intent(NewPlanActivity.ct, MainActivity.class);
        NewPlanActivity.ct.startActivity(it);
    }
}
